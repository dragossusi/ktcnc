package ro.dragossusi.editor.impl

import ro.dragossusi.editor.Editor
import ro.dragossusi.editor.EditorLoader
import ro.dragossusi.editor.reader.EditorReader
import ro.dragossusi.editor.textlines.EmptyTextLines
import okio.Path

class EditorLoaderImpl(private val reader: EditorReader) : EditorLoader {

    override fun loadEditor(path: Path): Editor {
        return Editor(file = path) {
            try {
                with(reader) { path.readTextLines(this@Editor) }
            } catch (e: Throwable) {
                e.printStackTrace()
                EmptyTextLines
            }
        }
    }
}
