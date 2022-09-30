package buildsrc.convention

plugins {
    kotlin("multiplatform") apply false
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Deps.Atrium.fluentCommon)
            }
        }
    }
}
