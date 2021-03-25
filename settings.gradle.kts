@file:Suppress("LocalVariableName")

rootProject.name = "skrape-it"
include(
    "assertions",
    "dsl",
    "examples:scraping",
    "fetcher:basis-fetcher",
    "fetcher:http-fetcher",
    "fetcher:ktor-fetcher",
    "fetcher:browser-fetcher",
    "html-parser",
    "integrationtests",
    "ktor-extension",
    "mock-mvc-extension",
    "test-utils"
)

pluginManagement {
    val kotlin_version: String by settings
    repositories {
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version kotlin_version
        // Temporarily at 1.4.30 until version matches kotlin version (1.4.31)
        id("org.jetbrains.dokka") version "1.4.30"
        id("com.adarshr.test-logger") version "2.1.1"
        jacoco
        id("com.github.ben-manes.versions") version "0.38.0"
        id("se.patrikerdes.use-latest-versions") version "0.2.15"
        id("io.gitlab.arturbosch.detekt") version "1.16.0"
        id("com.vanniktech.maven.publish") version "0.13.0"
    }
}
