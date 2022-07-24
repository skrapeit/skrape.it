plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
}

dependencies {
    api(projects.htmlParser)
    api(projects.dsl)
    api(projects.fetcher.baseFetcher)
}
