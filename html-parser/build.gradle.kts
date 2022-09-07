plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.kover
}

kotlin {
    jvm {}
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.dsl)
                api(projects.fetcher.baseFetcher)
                api(projects.fetcher.browserFetcher)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(Deps.jsoup)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(projects.testUtils)
            }
        }
    }
}
