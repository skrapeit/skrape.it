plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kotlin-multiplatform`
}

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.fetcher.baseFetcher)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientApache)
                implementation(Deps.Ktor.clientLogging)
                implementation(Deps.logback)
                implementation(Deps.log4jOverSlf4j)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(projects.testUtils)
            }
        }
    }
}
