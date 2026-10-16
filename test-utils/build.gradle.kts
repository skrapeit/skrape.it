plugins {
    id("kotlin-jvm-convention")
}

dependencies {
    compileOnly(projects.htmlParser)

    api(libs.wiremock.jre8)

    implementation(libs.testcontainers)
    implementation(libs.testcontainers.junit.jupiter)

    testImplementation(libs.rest.assured.kotlin.extensions)
}
