rootProject.name = "skrapeit"

include(
    ":assertions",
    ":dsl",
    ":examples:scraping",
    ":examples:use-pre-release-version",
    ":fetcher:base-fetcher",
    ":fetcher:http-fetcher",
    ":fetcher:async-fetcher",
    ":fetcher:browser-fetcher",
    ":fetcher:chrome-fetcher",
    ":html-parser",
    ":integrationtests",
    ":ktor-extension",
    ":mock-mvc-extension",
    ":test-utils"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


apply(from = "./buildSrc/repositories.settings.gradle.kts")

@Suppress("UnstableApiUsage") // Central declaration of repositories is an incubating feature
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

// Gradle Plugin versions are defined in ./buildSrc/build.gradle.kts
