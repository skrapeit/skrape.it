plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kmp`
}


kotlin {
    jvm {}
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.dsl)
                api(Deps.KotlinX.Coroutines.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(Deps.jsoup)
                implementation(Deps.Kotlin.reflect) {
                    because("to support Result#extractIt by creating instance of a class")
                }
                api(Deps.KotlinX.Coroutines.jdk8)
            }
        }

        val jvmTest by getting {

            dependencies {
                implementation(projects.testUtils)

                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientApache)
            }
        }
    }
}
