plugins {
    buildsrc.convention.`kotlin-jvm`
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

    // TODO figure out what jsExecutionImplementation is for
    "jsExecutionImplementation"(project(path = ":fetcher:browser-fetcher", configuration = "default"))

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
