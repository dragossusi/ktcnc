package editor

import ro.dragossusi.editor.Editor

data class EditorState(
    val editor: Editor,
    val settings: EditorSettings = EditorSettings()
)