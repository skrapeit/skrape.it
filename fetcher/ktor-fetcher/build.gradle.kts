plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(project(":fetcher:base-fetcher"))
    implementation(Deps.Ktor.client)
    implementation(Deps.Ktor.clientApache)
//    implementation(Deps.Ktor.clientLogging)
//    implementation(Deps.logback)
//    implementation(Deps.log4jOverSlf4j)
    testImplementation(project(path = ":test-utils", configuration = "default"))
}
