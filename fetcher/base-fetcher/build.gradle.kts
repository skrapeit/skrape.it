@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.dsl)
                implementation(Deps.Kotlin.reflect) {
                    because("to support Result#extractIt by creating instance of a class")
                }
                api(Deps.KotlinX.Coroutines.jdk8)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.wireMock)
                implementation(projects.testUtils)
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientApache)
            }
        }
    }
}

