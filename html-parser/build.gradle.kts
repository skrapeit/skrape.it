plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
    buildsrc.convention.kover
}

java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}

dependencies {
    implementation(projects.dsl)
    api(projects.fetcher.baseFetcher)
    api(Deps.jsoup)

    "jsExecutionImplementation"(projects.fetcher.browserFetcher)

    testImplementation(projects.testUtils)
}
