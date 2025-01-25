@Suppress("UnstableApiUsage")
dependencyResolutionManagement {

    repositories {
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "ktobs"

include("ktobs-core")
include("ktobs-ktor")
include("sample")
include("tests")
