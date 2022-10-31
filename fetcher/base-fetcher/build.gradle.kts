@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
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
        val jvmMain by getting {
            dependencies {
                implementation(projects.dsl)
                implementation(Deps.Ktor.clientCIO)
                implementation(Deps.Kotlin.reflect) {
                    because("to support Result#extractIt by creating instance of a class")
                }
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

