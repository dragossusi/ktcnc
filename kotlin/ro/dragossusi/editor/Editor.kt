package ro.dragossusi.editor

import ro.dragossusi.editor.textlines.TextLines
import kotlinx.coroutines.CoroutineScope
import okio.Path

class Editor constructor(
    val file: Path,
    val lines: CoroutineScope.() -> TextLines,
)
