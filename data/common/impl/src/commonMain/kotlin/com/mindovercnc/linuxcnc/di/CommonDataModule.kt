package com.mindovercnc.linuxcnc.di

import ro.dragossusi.editor.di.EditorThemeModule
import com.mindovercnc.linuxcnc.*
import com.mindovercnc.repository.*
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
val CommonDataModule =
    DI.Module("common_data") {
        import(EditorThemeModule)
        bindSingleton<EmcMessagesRepository> { EmcMessagesRepositoryImpl(instance(), instance()) }
        bindSingleton<CncMessagesRepository> { CncMessagesRepositoryImpl(instance()) }

        bindSingleton<TaskStatusRepository> { TaskStatusRepositoryImpl(instance()) }
        bindSingleton<MotionStatusRepository> { MotionStatusRepositoryImpl(instance()) }
        bindSingleton<IoStatusRepository> { IoStatusRepositoryImpl(instance()) }

        bindSingleton<ActiveLimitsRepository> { ActiveLimitsRepositoryImpl() }
    }
