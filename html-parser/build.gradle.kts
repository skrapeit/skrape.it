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
    implementation(project(":fetcher:basis-fetcher"))
    implementation("org.jsoup:jsoup:$jsoupVersion")

    "jsExecutionImplementation"(project(path = ":fetcher:browser-fetcher", configuration = "default"))
}
