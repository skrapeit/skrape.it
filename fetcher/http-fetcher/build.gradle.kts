plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kmp`
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
                implementation(Deps.Ktor.clientApache)
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientLogging)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(projects.testUtils)
            }
        }
    }
}
