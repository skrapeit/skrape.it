plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    testImplementation(Deps.jetbrainsAnnotations)
    testImplementation(projects.testUtils)
    testImplementation(projects.htmlParser)
    testImplementation(projects.assertions)
    testImplementation(projects.fetcher.baseFetcher)
    testImplementation(projects.fetcher.httpFetcher)
    testImplementation(projects.fetcher.browserFetcher)
    testImplementation(projects.fetcher.asyncFetcher)
}
