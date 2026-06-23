package ro.dragossusi.editor.type

import okio.Path

interface EditorFileTypeHandler {
  fun getFileType(path: Path): EditorFileType
}
