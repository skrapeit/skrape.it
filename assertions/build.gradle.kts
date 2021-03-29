plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    api(project(":html-parser"))
    api(project(":dsl"))
    api(project(":base-fetcher"))
}
