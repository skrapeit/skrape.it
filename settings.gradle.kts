@file:Suppress("LocalVariableName")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
        id("org.jetbrains.dokka") version kotlin_version
        id("com.adarshr.test-logger") version "3.1.0"
        `java-library`
        `maven-publish`
        signing
        id("org.jetbrains.kotlinx.kover") version "0.5.0-RC"
        id("com.github.ben-manes.versions") version "0.39.0"
        id("se.patrikerdes.use-latest-versions") version "0.2.18"
        id("io.gitlab.arturbosch.detekt") version "1.19.0"
        id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    }
}
