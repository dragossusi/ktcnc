package di

import AppConfig
import Communication
import com.mindovercnc.linuxcnc.initializer.KtlCncInitializer
import initializer.Initializer
import initializer.SimpleInitializer
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import startup.AppDirInitializer
import startup.StatusWatchInitializer

fun initializerModule(appConfig: AppConfig) = DI.Module("initializer") {
    bindSingleton { AppDirInitializer(instance()) }

    bindSingleton { StatusWatchInitializer(instance(), instance()) }

    bindSingleton("app") {
        val appDirInitializer: AppDirInitializer = instance()
        val databaseInitializer: Initializer = instance("database")
        val statusWatchInitializer: StatusWatchInitializer = instance()

        val steps = listOfNotNull(
            appDirInitializer,
            instance<KtlCncInitializer>().takeIf { appConfig.communication is Communication.Local },
            databaseInitializer,
            statusWatchInitializer
        )

        SimpleInitializer(*steps.toTypedArray())
    }

    import(LocalInitializerModule)
}

val LocalInitializerModule = DI.Module("local_initializer") {
    bindSingleton { KtlCncInitializer(instance("app_dir")) }
}