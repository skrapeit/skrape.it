plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(project(":fetcher:basis-fetcher"))
    implementation(Deps.Ktor.client)
    implementation(Deps.Ktor.clientApache)
    testImplementation(project(path = ":test-utils", configuration = "default"))
}
