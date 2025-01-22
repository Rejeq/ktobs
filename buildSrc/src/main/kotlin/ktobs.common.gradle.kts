val projectLibs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val javaToolachain =
    JavaLanguageVersion.of(
        projectLibs.findVersion("java-toolchain").get().requiredVersion,
    )

plugins {
    kotlin("jvm")
    id("ktobs.detekt")
}

kotlin {
    jvmToolchain(jdkVersion = javaToolachain.asInt())
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
