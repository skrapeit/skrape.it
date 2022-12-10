package buildsrc.convention

plugins {
    id("buildsrc.convention.kotlin-multiplatform")
    kotlin("multiplatform")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    js(IR) {
        nodejs {
            //Setup tests to use mocha and supply the project path as an environment-variable to enable location of resources
            testTask {
                environment("PROJECT_PATH", projectDir.absolutePath)
                environment("ROOT_PROJECT_PATH", rootProject.projectDir.absolutePath)
                /*useMocha {
                    this.timeout = "100000"
                }*/
            }
            binaries.library()
        }
        useCommonJs()
    }
}