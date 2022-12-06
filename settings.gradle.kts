rootProject.name = "skrapeit"

include(
    ":assertions",
    ":dsl",
    ":examples:scraping",
    ":examples:use-pre-release-version",
    ":fetcher",
    ":html-parser",
    ":integrationtests",
    ":ktor-extension",
    ":mock-mvc-extension",
    ":test-utils"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
