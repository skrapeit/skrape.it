@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    implementation(projects.dsl)
    implementation(Deps.Kotlin.reflect) {
        because("to support Result#extractIt by creating instance of a class")
    }
    api(Deps.KotlinX.Coroutines.jdk8)

    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation(Deps.Ktor.client)
    testImplementation(Deps.Ktor.clientApache)
}
