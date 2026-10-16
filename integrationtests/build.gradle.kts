plugins {
    id("kotlin-jvm-convention")
}

dependencies {
    testImplementation(libs.jetbrains.annotations)
    testImplementation(projects.testUtils)
    testImplementation(projects.htmlParser)
    testImplementation(projects.assertions)
    testImplementation(projects.fetcher.baseFetcher)
    testImplementation(projects.fetcher.httpFetcher)
    testImplementation(projects.fetcher.browserFetcher)
    testImplementation(projects.fetcher.asyncFetcher)
}
