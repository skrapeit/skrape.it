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
    implementation(project(":dsl"))
    api(project(":base-fetcher"))
    api(Deps.jsoup)

    "jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
