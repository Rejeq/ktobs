plugins {
    id("ktobs.common")
}

dependencies {
    testImplementation(project(":ktobs-core"))
    testImplementation(project(":ktobs-ktor"))

    testImplementation(kotlin("test"))
    testImplementation(libs.ktor.cio)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.core)
    testImplementation(libs.ktor.json)
}

tasks.test {
    useJUnitPlatform()
}
