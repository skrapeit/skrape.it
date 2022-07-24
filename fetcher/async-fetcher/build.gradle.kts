plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kmp`
}

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.fetcher.baseFetcher)
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientApache)
                implementation(Deps.Ktor.clientLogging)
            }
        }

        val jvmMain by getting {
            dependencies {
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
