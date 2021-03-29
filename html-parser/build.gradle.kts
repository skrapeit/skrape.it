plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}

dependencies {
    val jsoupVersion = "1.13.1"

    implementation(project(":dsl"))
    api(project(":base-fetcher"))
    api("org.jsoup:jsoup:$jsoupVersion")

    "jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
