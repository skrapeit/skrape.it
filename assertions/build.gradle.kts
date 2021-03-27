plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    implementation(project(":html-parser"))
    implementation(project(":dsl"))
    implementation(project(":fetcher:base-fetcher"))
}
