@file:Suppress("PropertyName")

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    api(projects.baseFetcher)
    implementation(Deps.Ktor.client)
    implementation(Deps.Ktor.clientApache)

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
