plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

version = Versions.app

kotlin {
    jvm()
    if (Platforms.jsEnabled) {
        js(IR) {
            browser()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Coroutines.core)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(Libs.Compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(Libs.okio)
                implementation(Libs.Kodein.compose)
                implementation(project(":editor"))
                implementation(project(":frontend:scroll"))
            }
        }
    }
}
