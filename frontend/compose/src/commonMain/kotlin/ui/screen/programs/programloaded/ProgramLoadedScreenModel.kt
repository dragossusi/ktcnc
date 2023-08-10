package ui.screen.programs.programloaded

import actor.PathActor
import actor.ProgramData
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.mindovercnc.dispatchers.IoDispatcher
import com.mindovercnc.editor.Editor
import com.mindovercnc.editor.EditorLoader
import com.mindovercnc.linuxcnc.gcode.IniFileRepository
import editor.EditorSettings
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.Path
import org.jetbrains.skia.Point
import screen.uimodel.PositionModel
import usecase.*
import usecase.model.ActiveCode
import usecase.model.PathUiState
import usecase.model.VisualTurningState
import kotlin.math.min

class ProgramLoadedScreenModel(
    private val file: Path,
    private val gCodeUseCase: GCodeUseCase,
    offsetsUseCase: OffsetsUseCase,
    positionUseCase: PositionUseCase,
    private val activeCodesUseCase: ActiveCodesUseCase,
    private val programsUseCase: ProgramsUseCase,
    private val editorLoader: EditorLoader,
    spindleUseCase: SpindleUseCase,
    feedUseCase: FeedUseCase,
    iniFileRepository: IniFileRepository,
    private val manualToolChangeUseCase: ManualToolChangeUseCase,
    ioDispatcher: IoDispatcher
) : StateScreenModel<ProgramLoadedScreenModel.State>(State()) {

    data class State(
        val editor: Editor? = null,
        val settings: EditorSettings = EditorSettings(),
        val positionModel: PositionModel? = null,
        val currentWcs: String = "--",
        val currentFolder: Path? = null,
        val visualTurningState: VisualTurningState = VisualTurningState(),
        val activeCodes: List<ActiveCode> = emptyList(),
        val machineStatus: MachineStatus = MachineStatus(),
        val toolChangeModel: ToolChangeModel? = null,
    )

    // how much free space to have around the drawing
    private val viewportPadding = 70 // px
    private val toolTrace = mutableListOf<Point>()

    init {
        coroutineScope.launch(ioDispatcher.dispatcher) {
            val editor = editorLoader.loadEditor(file)
            mutableState.update { it.copy(editor = editor) }
        }

        // programsUseCase.loadProgram(file)
        val machineLimits =
            with(iniFileRepository.getActiveLimits()) {
                MachineLimits(
                    xMin = xMinLimit!!,
                    xMax = xMaxLimit!!,
                    zMin = zMinLimit!!,
                    zMax = zMaxLimit!!
                )
            }
        mutableState.update {
            it.copy(visualTurningState = it.visualTurningState.copy(machineLimits = machineLimits))
        }

        offsetsUseCase.currentOffset
            .filterNotNull()
            .onEach { wcs ->
                mutableState.update {
                    it.copy(
                        currentWcs = wcs.coordinateSystem,
                        visualTurningState = it.visualTurningState.copyWithWcs(wcs)
                    )
                }
            }
            .launchIn(coroutineScope)

        coroutineScope.launch {
            val pathElements = gCodeUseCase.getPathElements(file)
            val initialPathActor = PathActor(pathElements)
            val defaultPixelsPerUnit =
                calculateDefaultPxPerUnit(
                    viewportSize = mutableState.value.visualTurningState.viewportSize,
                    programSize = initialPathActor.programSize,
                )
            val pathActor = initialPathActor.rescaled(defaultPixelsPerUnit)
            val pathUiState = PathUiState(pathActor)
            mutableState.update {
                val rulers = it.visualTurningState.programRulers.rescaled(defaultPixelsPerUnit)
                it.copy(
                    visualTurningState =
                    it.visualTurningState.copy(
                        pathUiState = pathUiState,
                        programRulers = rulers,
                        defaultPixelsPerUnit = defaultPixelsPerUnit,
                        translate =
                        pathUiState.getInitialTranslate(viewportSize = it.visualTurningState.viewportSize)
                    )
                )
            }
        }

        positionUseCase
            .getToolPosition()
            .onEach { point ->
                toolTrace.add(point)
                mutableState.update {
                    it.copy(
                        visualTurningState =
                        it.visualTurningState.copy(
                            toolPosition = point,
                        )
                    )
                }
            }
            .launchIn(coroutineScope)

        activeCodesUseCase
            .getActiveCodes()
            .onEach { codes -> mutableState.update { it.copy(activeCodes = codes) } }
            .launchIn(coroutineScope)

        programsUseCase.uiModel
            .onEach { model -> mutableState.update { it.copy(positionModel = model) } }
            .launchIn(coroutineScope)

        spindleUseCase
            .spindleFlow()
            .onEach { model ->
                mutableState.update {
                    it.copy(
                        machineStatus =
                        it.machineStatus.copy(
                            spindleOverride = model.spindleOverride,
                            actualSpindleSpeed = model.actualRpm
                        )
                    )
                }
            }
            .launchIn(coroutineScope)

        feedUseCase
            .feedFlow()
            .onEach { model ->
                mutableState.update {
                    it.copy(
                        machineStatus =
                        it.machineStatus.copy(
                            feedOverride = model.feedOverride,
                        )
                    )
                }
            }
            .launchIn(coroutineScope)

        manualToolChangeUseCase.toolToChange
            .onEach { toolNo ->
                println("---update state: $toolNo")
                mutableState.update {
                    it.copy(
                        toolChangeModel =
                        when {
                            toolNo != null -> ToolChangeModel(toolNo)
                            else -> null
                        }
                    )
                }
            }
            .launchIn(coroutineScope)
    }

    fun zoomOut() = setNewScale { it / 1.1f }

    fun zoomIn() = setNewScale { it * 1.1f }

    fun zoomBy(factor: Float) = setNewScale { it * factor }

    private fun setNewScale(block: (Float) -> Float) {
        mutableState.update {
            val newScale = block(mutableState.value.visualTurningState.scale)
            val pixelPerUnit = it.visualTurningState.defaultPixelsPerUnit * newScale
            val pathUiState = it.visualTurningState.pathUiState.rescaled(pixelPerUnit)
            val rulers = it.visualTurningState.programRulers.rescaled(pixelPerUnit)
            it.copy(
                visualTurningState =
                it.visualTurningState.copy(
                    scale = newScale,
                    pathUiState = pathUiState,
                    programRulers = rulers,
                    translate =
                    pathUiState.getInitialTranslate(viewportSize = it.visualTurningState.viewportSize)
                )
            )
        }
    }

    fun translate(offset: Offset) {
        mutableState.update {
            it.copy(
                visualTurningState =
                it.visualTurningState.copy(translate = it.visualTurningState.translate.plus(offset))
            )
        }
        // println("Translate: ${mutableState.value.visualTurningState.translate}")
    }

    fun setViewportSize(size: IntSize) {
        mutableState.update {
            it.copy(visualTurningState = it.visualTurningState.copy(viewportSize = size))
        }
    }

    fun runProgram() {
        programsUseCase.runProgram()
    }

    fun stopProgram() {
        programsUseCase.stopProgram()
    }

    fun confirmToolChanged() {
        coroutineScope.launch { manualToolChangeUseCase.confirmToolChange() }
    }

    fun cancelToolChange() {
        manualToolChangeUseCase.cancelToolChange()
    }

    fun onActiveCodeClicked(activeCode: ActiveCode) {
        activeCodesUseCase.getCodeDescription(activeCode)
    }

    private fun calculateDefaultPxPerUnit(
        viewportSize: IntSize,
        programSize: ProgramData.ProgramSize,
    ): Float {
        val drawableWidth = viewportSize.width - viewportPadding
        val drawableHeight = viewportSize.height - viewportPadding
        val widthRatio = drawableWidth.div(programSize.width)
        val heightRatio = drawableHeight.div(programSize.height)
        return min(widthRatio, heightRatio)
    }
}
