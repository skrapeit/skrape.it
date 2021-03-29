@file:Suppress("LocalVariableName")

rootProject.name = "skrapeit"

include(
    "assertions",
    "dsl",
    "examples:scraping",
    "examples:use-pre-release-version",
    "base-fetcher",
    "http-fetcher",
    "async-fetcher",
    "browser-fetcher",
    "html-parser",
    "integrationtests",
    "ktor-extension",
    "mock-mvc-extension",
    "test-utils"
)

project(":base-fetcher").projectDir = file("fetcher/base-fetcher")
project(":http-fetcher").projectDir = file("fetcher/http-fetcher")
project(":browser-fetcher").projectDir = file("fetcher/browser-fetcher")
project(":async-fetcher").projectDir = file("fetcher/async-fetcher")

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
        `java-library`
        `maven-publish`
        signing
        id("com.github.ben-manes.versions") version "0.38.0"
        id("se.patrikerdes.use-latest-versions") version "0.2.15"
        id("io.gitlab.arturbosch.detekt") version "1.16.0"
        id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
        id("com.github.johnrengelman.shadow") version "6.1.0"
    }
}
