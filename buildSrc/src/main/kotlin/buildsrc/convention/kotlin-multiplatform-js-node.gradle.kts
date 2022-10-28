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
                environment("ROOT_PROJECT_PATH", rootProject.projectDir.absolutePath)
                useMocha {
                    this.timeout = "5000"
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