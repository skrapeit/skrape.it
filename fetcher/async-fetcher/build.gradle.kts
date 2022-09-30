plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`publish-multiplatform`
}


kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.fetcher.baseFetcher)
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
