plugins {
    id("kotlin-library-convention")
}

dependencies {
    api(projects.fetcher.baseFetcher)
    implementation(projects.fetcher.asyncFetcher)

    testImplementation(projects.testUtils)
}
