package com.mindovercnc.repository

import ro.dragossusi.editor.EditorTheme
import ro.dragossusi.editor.EditorThemeVariant

/** Repository for [EditorThemeVariant]. */
interface EditorThemeRepository {
  suspend fun getEditorTheme(): EditorTheme?
}
