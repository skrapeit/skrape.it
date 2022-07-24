package buildsrc.convention

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}

kotlin {
    explicitApi()

    targets.all {
        compilations.all {
            kotlinOptions {
                apiVersion = "1.5"
                languageVersion = "1.7"
            }
        }
    }
}
