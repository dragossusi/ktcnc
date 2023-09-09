package com.mindovercnc.linuxcnc.screen.manual.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.mindovercnc.linuxcnc.screen.manual.root.ManualRootComponent.Child
import com.mindovercnc.linuxcnc.screen.manual.root.ManualRootComponent.Config
import com.mindovercnc.linuxcnc.screen.manual.simplecycles.SimpleCyclesComponent
import com.mindovercnc.linuxcnc.screen.manual.simplecycles.SimpleCyclesScreenModel
import com.mindovercnc.linuxcnc.screen.manual.turning.ManualTurningComponent
import com.mindovercnc.linuxcnc.screen.manual.turning.ManualTurningScreenModel
import com.mindovercnc.linuxcnc.screen.manual.turningsettings.TurningSettingsComponent
import com.mindovercnc.linuxcnc.screen.manual.turningsettings.TurningSettingsScreenModel
import com.mindovercnc.linuxcnc.screen.manual.virtuallimits.VirtualLimitsComponent
import com.mindovercnc.linuxcnc.screen.manual.virtuallimits.VirtualLimitsScreenModel
import com.mindovercnc.model.SimpleCycle
import org.kodein.di.*

class ManualRootScreenModel(
    private val di: DI,
    componentContext: ComponentContext,
) : ManualRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = Config.Turning,
            childFactory = ::createChild
        )

    override val childStack: Value<ChildStack<*, Child>> = _childStack

    override fun openVirtualLimits() {
        navigation.push(Config.VirtualLimits)
    }

    override fun openSimpleCycles(simpleCycle: SimpleCycle) {
        navigation.push(Config.SimpleCycles(simpleCycle))
    }

    override fun openTurningSettings() {
        navigation.push(Config.TurningSettings)
    }

    override fun navigateUp() {
        navigation.pop()
    }

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Turning -> Child.Turning(manualTurningComponent())
            is Config.SimpleCycles ->
                Child.SimpleCycles(simpleCyclesComponent(config.simpleCycle, componentContext))
            Config.VirtualLimits -> Child.VirtualLimits(virtualLimitsComponent())
            Config.TurningSettings -> Child.TurningSettings(turningSettingsComponent())
        }
    }

    private fun manualTurningComponent(): ManualTurningComponent {
        return di.direct.instance<ManualTurningScreenModel>()
    }

    private fun simpleCyclesComponent(
        simpleCycle: SimpleCycle,
        componentContext: ComponentContext
    ): SimpleCyclesComponent {
        val subDI = subDI(di) { bindProvider { simpleCycle } }
        return SimpleCyclesScreenModel(subDI, componentContext)
    }

    private fun virtualLimitsComponent(): VirtualLimitsComponent {
        return di.direct.instance<VirtualLimitsScreenModel>()
    }

    private fun turningSettingsComponent(): TurningSettingsComponent {
        return di.direct.instance<TurningSettingsScreenModel>()
    }
}
