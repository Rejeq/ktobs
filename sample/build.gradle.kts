plugins {
    id("ktobs.common")

    application
}

dependencies {
    implementation(project(":ktobs-core"))
    implementation(project(":ktobs-ktor"))

    implementation(libs.ktor.cio)
    implementation(libs.ktor.core)
    implementation(libs.ktor.json)
    implementation(libs.ktor.logging)
    implementation(libs.logback)
}

application {
    mainClass = "com.rejeq.ktobs.sample.AppKt"
}
