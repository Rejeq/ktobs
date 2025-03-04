plugins {
    id("ktobs.common")
    id("ktobs.multiplatform")
    id("ktobs.publish")
}

kotlin {

    sourceSets {
        commonMain.dependencies {
            implementation(project(":ktobs-core"))

            implementation(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.websocket)
        }
    }
}

mavenPublishing {
    pom {
        name = "ktobs-ktor"
        description = "Ktor websocket session for ktobs-core"
    }
}
