plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation("org.jetbrains:annotations:19.0.0")
    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(project(path = ":html-parser", configuration = "default"))
    testImplementation(project(path = ":assertions", configuration = "default"))
    testImplementation(project(path = ":fetcher:base-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:http-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:browser-fetcher", configuration = "default"))
    testImplementation(project(path = ":fetcher:async-fetcher", configuration = "default"))
}
