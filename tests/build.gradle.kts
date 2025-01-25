plugins {
    id("ktobs.common")
    id("ktobs.multiplatform")
}

kotlin {
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonTest.dependencies {
            implementation(project(":ktobs-core"))
            implementation(project(":ktobs-ktor"))

            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.core)
            implementation(libs.ktor.json)
        }

        val defaultUtils by creating {
            dependsOn(commonTest.get())

            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }

        listOf(
            jvmTest,
            nativeTest,
        ).forEach {
            it.get().dependsOn(defaultUtils)
        }
    }
}
