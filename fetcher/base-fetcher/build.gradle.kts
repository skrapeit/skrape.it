@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    kotlin("multiplatform")
}

kotlin {
    js() {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.Ktor.client)
                implementation(projects.dsl)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-common:0.18.0")
            }
        }
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
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB:0.18.0")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:0.18.0")
            }
        }
    }
}

