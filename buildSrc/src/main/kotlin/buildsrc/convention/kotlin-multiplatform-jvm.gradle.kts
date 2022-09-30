package buildsrc.convention

plugins {
    id("buildsrc.convention.kotlin-multiplatform")
    kotlin("multiplatform")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
            systemProperties = mapOf(
                "junit.jupiter.execution.parallel.enabled" to true,
                "junit.jupiter.execution.parallel.mode.default" to "concurrent",
                "junit.jupiter.execution.parallel.mode.classes.default" to "concurrent"
            )
        }

        //Atrium requires JVM-Target 11 so compile the tests for JVM 11
        val test by compilations.getting {
            kotlinOptions {
                jvmTarget = "11"
            }
        }

        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs += listOf("-Xjsr305=strict")
                apiVersion = "1.7"
                languageVersion = "1.7"
            }
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(Deps.jUnit)
                implementation(Deps.strikt)
                implementation(Deps.Mockk.mockk)
                implementation(Deps.Mockk.dslJvm)
                implementation(Deps.wireMock)
                implementation(Deps.Atrium.fluentJvm)
            }
        }
    }
}