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

apply(from = "./buildSrc/repositories.settings.gradle.kts")

@Suppress("UnstableApiUsage") // Central declaration of repositories is an incubating feature
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
}

// Gradle Plugin versions are defined in ./buildSrc/build.gradle.kts
