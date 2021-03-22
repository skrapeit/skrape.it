plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(project(path = ":html-parser", configuration = "default"))
    testImplementation(project(path = ":assertions", configuration = "default"))
    testImplementation(project(path = ":fetcher:basis-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:http-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:browser-fetcher", configuration = "default"))
}
