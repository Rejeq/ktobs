plugins {
    id("ktobs.common")
    id("ktobs.publish")
}

dependencies {
    implementation(project(":ktobs-core"))

    implementation(libs.ktor.core)
    implementation(libs.ktor.json)
    implementation(libs.ktor.websocket)
}
