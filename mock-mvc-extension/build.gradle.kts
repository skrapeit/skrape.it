@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(project(":core"))

    implementation("org.springframework:spring-webmvc:5.2.9.RELEASE")
    implementation("org.springframework:spring-test:5.2.9.RELEASE")

    implementation("javax.servlet:javax.servlet-api:4.0.1")
}