@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

val projectLibs = extensions.getByType<VersionCatalogsExtension>().named("libs")

val javaToolchain = projectLibs.getJavaVersion("java-toolchain")
val javaTarget = projectLibs.getVersion("java-target")

kotlin {
    jvm {
        withJava()
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(javaTarget)
        }
    }

    jvmToolchain(javaToolchain.asInt())

    // Tests are disabled because they have issues with runBlocking() in web
    if (!project.isTests()) {
        js {
            nodejs()
        }

        wasmJs {
            nodejs()
        }

        // https://youtrack.jetbrains.com/issue/KTOR-7290/Support-wasmWasi-target
        if (!project.isDependOnKtor()) {
            wasmWasi {
                nodejs()
            }
        }
    }

    // According to https://kotlinlang.org/docs/native-target-support.html
    // Tier 1
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // Tier 2
    linuxX64()
    linuxArm64()
    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()
    iosArm64()

    // Tier 3
    mingwX64()
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    watchosDeviceArm64()
}

fun Project.isDependOnKtor(): Boolean = this.name == "ktobs-ktor" || isTests()

fun Project.isTests(): Boolean = this.name == "tests"

fun VersionCatalog.getVersion(name: String) =
    projectLibs.findVersion(name).get().requiredVersion

fun VersionCatalog.getJavaVersion(name: String) =
    JavaLanguageVersion.of(getVersion(name))
