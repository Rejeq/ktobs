plugins {
    id("ktobs.common")
    kotlin("jvm")

    application
}

kotlin {
    jvmToolchain(
        jdkVersion =
            libs.versions.java.toolchain
                .get()
                .toInt(),
    )
}

dependencies {
    implementation(project(":ktobs-core"))
    implementation(project(":ktobs-ktor"))

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.core)
    implementation(libs.ktor.json)
    implementation(libs.ktor.logging)
    implementation(libs.logback)
}

application {
    mainClass = "com.rejeq.ktobs.sample.AppKt"
}
