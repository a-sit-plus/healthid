import at.asitplus.gradle.Logger
import at.asitplus.gradle.kotest
import at.asitplus.gradle.serialization
import at.asitplus.gradle.setupDokka

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("at.asitplus.gradle.conventions")
    id("org.jetbrains.dokka")
    id("signing")
}

/* required for maven publication */
val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                api("at.asitplus.wallet:vck:5.4.2")
            }
        }
    }
}

val javadocJar = setupDokka(baseUrl = "https://github.com/a-sit-plus/eprescription/tree/main/")

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar)
            pom {
                name.set("Health ID Attestation")
                description.set("Use data representing Health ID as a SD-JWT credential, using VC-K")
                url.set("https://github.com/a-sit-plus/eprescription/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("nodh")
                        name.set("Christian Kollmann")
                        email.set("christian.kollmann@a-sit.at")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:a-sit-plus/eprescription.git")
                    developerConnection.set("scm:git:git@github.com:a-sit-plus/eprescription.git")
                    url.set("https://github.com/a-sit-plus/eprescription/")
                }
            }
        }
    }
    repositories {
        mavenLocal {
            signing.isRequired = false
        }
    }
}

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications)
}

