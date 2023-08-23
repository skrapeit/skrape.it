@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
    buildsrc.convention.kover
}

dependencies {
    implementation(projects.dsl)
    implementation(Deps.Kotlin.reflect) {
        because("to support Result#extractIt by creating instance of a class")
    }
    api(Deps.KotlinX.Coroutines.jdk8)

    testImplementation(projects.testUtils)
    testImplementation(Deps.Ktor.client)
    testImplementation(Deps.Ktor.clientApache)
}
