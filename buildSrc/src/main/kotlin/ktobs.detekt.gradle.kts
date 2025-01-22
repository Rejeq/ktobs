val projectLibs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val javaTarget =
    projectLibs
        .findVersion("java-target")
        .get()
        .requiredVersion

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config.setFrom("${project.rootDir}/detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = javaTarget

    reports {
        val detektDir = layout.buildDirectory.dir("reports/detekt")

        md.required.set(true)
        md.outputLocation.set(detektDir.map { it.file("detekt.md") })

        html.required.set(true)
        html.outputLocation.set(detektDir.map { it.file("detekt.html") })

        sarif.required.set(false)
        txt.required.set(false)
        xml.required.set(false)
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask> {
    jvmTarget = javaTarget
}
