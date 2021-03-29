@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    implementation(project(":dsl"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version") // needed for extractIt when creating instance

    testImplementation(project(path = ":test-utils", configuration = "default"))
    val ktorVersion = "1.5.2"
    testImplementation("io.ktor:ktor-client-core:$ktorVersion")
    testImplementation("io.ktor:ktor-client-apache:$ktorVersion")
}
