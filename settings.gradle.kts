@file:Suppress("LocalVariableName")

rootProject.name = "skrape-it"
include("core", "examples", "ktor-extension")

pluginManagement {
    val kotlin_version: String by settings
    repositories {
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version kotlin_version
        id("org.jetbrains.dokka") version kotlin_version
        id("com.adarshr.test-logger") version "2.1.0"
        jacoco
        id("com.github.ben-manes.versions") version "0.33.0"
        id("se.patrikerdes.use-latest-versions") version "0.2.15"
        id("io.gitlab.arturbosch.detekt") version "1.14.1"
        id("com.vanniktech.maven.publish") version "0.13.0"
    }
}
