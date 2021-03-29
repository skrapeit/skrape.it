@file:Suppress("PropertyName")

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    val kohttpVersion = "0.12.0"
    api(project(":base-fetcher"))
    api("io.github.rybalkinsd:kohttp:$kohttpVersion")
    api("com.squareup.okhttp3:okhttp:4.9.1") // Hotfix since new kohttp version has been released

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
