@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    implementation(projects.dsl)
    implementation(Deps.Kotlin.reflect) // needed for extractIt when creating instance
    implementation(Deps.KotlinX.Coroutines.core)
    implementation(Deps.KotlinX.Coroutines.jdk8)

    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(Deps.Ktor.client)
    testImplementation(Deps.Ktor.clientApache)
}
