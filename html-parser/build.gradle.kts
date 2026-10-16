plugins {
    id("kotlin-library-convention")
}

java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}

dependencies {
    implementation(projects.dsl)
    api(projects.fetcher.baseFetcher)
    api(libs.jsoup)

    "jsExecutionImplementation"(projects.fetcher.browserFetcher)

    testImplementation(projects.testUtils)
}
