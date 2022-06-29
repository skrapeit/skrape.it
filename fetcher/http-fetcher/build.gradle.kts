@file:Suppress("PropertyName")

plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(projects.baseFetcher)
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientApache)
                implementation(Deps.Ktor.clientLogging)
                implementation(Deps.logback)
                implementation(Deps.log4jOverSlf4j)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.wireMock)
                implementation(projects.testUtils)
            }
        }
    }
}
