package ro.dragossusi.editor.di

import ro.dragossusi.editor.EditorLoader
import ro.dragossusi.editor.EditorThemeLoader
import ro.dragossusi.editor.impl.EditorLoaderImpl
import ro.dragossusi.editor.impl.EditorThemeLoaderImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val EditorThemeModule = DI.Module("editor_theme") {
    bindProvider<EditorThemeLoader> { EditorThemeLoaderImpl(instance(), instance()) }
    bindProvider<EditorLoader> { EditorLoaderImpl(instance()) }
}