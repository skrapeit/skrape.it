package buildsrc.convention

plugins {
    id("buildsrc.convention.kotlin-multiplatform")
    kotlin("multiplatform")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    js {
        nodejs {
            //Setup tests to use mocha and supply the project path as an environment-variable to enable location of resources
            testTask {
                environment("PROJECT_PATH", projectDir.absolutePath)
                useMocha {

                }
            }
        }
        useCommonJs()
    }

    sourceSets {
        val jsTest by getting {
            dependencies {
                implementation(Deps.Atrium.fluentJs)
            }
        }
    }
}