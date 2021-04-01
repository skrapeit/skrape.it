@file:Suppress("PropertyName")

plugins {
    jacoco
    kotlin("jvm")
}

dependencies {
    api(project(":base-fetcher"))
    api(Deps.koHttp)
    api(Deps.okHttp) // Hotfix since new kohttp version has been released

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
