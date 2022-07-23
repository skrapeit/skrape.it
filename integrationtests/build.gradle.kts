plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    testImplementation(Deps.jetbrainsAnnotations)
    // TODO figure out if projects.project can be used instead of this format
    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(project(path = ":html-parser", configuration = "default"))
    testImplementation(project(path = ":assertions", configuration = "default"))
    testImplementation(project(path = ":fetcher:base-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:http-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:browser-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:async-fetcher", configuration = "default"))
}
