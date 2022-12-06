@file:Suppress("PropertyName")

plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
    buildsrc.convention.`kotlin-multiplatform-js-node`
    buildsrc.convention.`publish-multiplatform`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.Ktor.client)
                implementation(projects.dsl)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.testUtils)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientCIO)
                implementation(Deps.Kotlin.reflect)
            }
        }
        val jsTest by getting {
            dependencies {
                //TODO: If we don't depend on htmlPraser for the test the build fails for no apparent reason
                api(projects.htmlParser)
            }
        }
    }
}

