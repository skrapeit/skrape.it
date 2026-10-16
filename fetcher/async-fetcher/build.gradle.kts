plugins {
    id("kotlin-library-convention")
}

dependencies {
    implementation(projects.fetcher.baseFetcher)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.apache)
    implementation(libs.ktor.client.logging)
    implementation(libs.logback.classic)
    implementation(libs.log4j.over.slf4j)

    testImplementation(projects.testUtils)
}
