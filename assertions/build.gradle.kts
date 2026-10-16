plugins {
    id("kotlin-library-convention")
}

dependencies {
    api(projects.htmlParser)
    api(projects.dsl)
    api(projects.fetcher.baseFetcher)
}
