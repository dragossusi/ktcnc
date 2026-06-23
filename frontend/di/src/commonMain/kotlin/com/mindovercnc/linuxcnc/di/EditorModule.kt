package com.mindovercnc.linuxcnc.di

import ro.dragossusi.editor.impl.EditorLoaderImpl
import ro.dragossusi.editor.type.EditorFileTypeHandler
import ro.dragossusi.editor.type.EditorFileTypeHandlerImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val EditorModule = DI.Module("editor") {
    bindSingleton { EditorLoaderImpl(instance()) }

    //    bindSingleton<EditorReader> { PathEditorReader(instance()) }
    bindSingleton<EditorFileTypeHandler> { EditorFileTypeHandlerImpl }
}
