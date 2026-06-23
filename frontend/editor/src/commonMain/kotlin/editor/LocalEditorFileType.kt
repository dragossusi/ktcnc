package editor

import androidx.compose.runtime.compositionLocalOf
import ro.dragossusi.editor.type.EditorFileType

val LocalEditorFileType = compositionLocalOf {
    EditorFileType.NORMAL
}