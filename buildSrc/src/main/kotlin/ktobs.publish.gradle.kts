import org.gradle.api.publish.maven.MavenPublication

group = "com.rejeq.ktobs"
version = "5.5.4"

plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("ktobs") {
            from(components["java"])
        }
    }
}
