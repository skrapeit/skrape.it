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
    //This breaks kotlin npm resolution
    //repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
}

// Gradle Plugin versions are defined in ./buildSrc/build.gradle.kts
