plugins {
    jacoco
    kotlin("jvm")
}

java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}

dependencies {
    implementation(projects.dsl)
    api(projects.baseFetcher)
    api(Deps.jsoup)

    "jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
