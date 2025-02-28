import com.vanniktech.maven.publish.SonatypeHost

group = "io.github.rejeq"
version = "0.3.0"

plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    pom {
        inceptionYear = "2025"
        url = "https://github.com/rejeq/ktobs/"

        licenses {
            license {
                name = "MIT License"
                url = "http://www.opensource.org/licenses/mit-license.php"
            }
        }

        developers {
            developer {
                id = "rejeq"
                name = "rejeq"
                url = "https://github.com/rejeq/"
            }
        }

        scm {
            url = "https://github.com/rejeq/ktobs/"
            connection = "scm:git:git://github.com/rejeq/ktobs.git"
            developerConnection = "scm:git:ssh://git@github.com/rejeq/ktobs.git"
        }
    }
}
