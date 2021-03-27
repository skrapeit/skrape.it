@file:Suppress("PropertyName")

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val kohttpVersion = "0.12.0"
    implementation(project(":fetcher:base-fetcher"))
    implementation("io.github.rybalkinsd:kohttp:$kohttpVersion")
    implementation("com.squareup.okhttp3:okhttp:4.9.1") // Hotfix since new kohttp version has been released

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
