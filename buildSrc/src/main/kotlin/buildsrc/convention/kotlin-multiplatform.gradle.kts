package buildsrc.convention

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}

kotlin {
    explicitApi()

    targets.configureEach {
        compilations.configureEach {
            kotlinOptions {
                apiVersion = "1.5"
                languageVersion = "1.7"
            }
        }
    }
}
