@file:Suppress("LocalVariableName")

rootProject.name = "skrape-it"
include("core")

pluginManagement {
    val kotlin_version: String by settings
    repositories {
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version kotlin_version
        id("com.adarshr.test-logger") version "2.1.0"
        id("com.github.ben-manes.versions") version "0.29.0"
        jacoco
        id("org.jetbrains.dokka") version "0.10.1"
        id("se.patrikerdes.use-latest-versions") version "0.2.14"
        id("io.gitlab.arturbosch.detekt") version "1.10.0"
        id("com.vanniktech.maven.publish") version "0.12.0"
    }
}
