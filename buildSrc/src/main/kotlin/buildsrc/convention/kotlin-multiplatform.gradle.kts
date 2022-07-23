package buildsrc.convention

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}
