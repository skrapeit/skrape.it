plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
    buildsrc.convention.kover
}

dependencies {
    api(projects.htmlParser)
    api(projects.dsl)
    api(projects.fetcher.baseFetcher)
}
