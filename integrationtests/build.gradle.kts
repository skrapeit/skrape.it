plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation("org.jetbrains:annotations:19.0.0")
    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(project(path = ":html-parser", configuration = "default"))
    testImplementation(project(path = ":assertions", configuration = "default"))
    testImplementation(project(path = ":base-fetcher", configuration = "default"))
    testImplementation(project(path = ":http-fetcher", configuration = "default"))
    testImplementation(project(path = ":browser-fetcher", configuration = "default"))
    testImplementation(project(path = ":async-fetcher", configuration = "default"))
}
