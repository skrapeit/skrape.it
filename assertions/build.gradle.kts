plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`maven-publishing`
}

dependencies {
    api(projects.htmlParser)
    api(projects.dsl)
    api(projects.fetcher.baseFetcher)
}
