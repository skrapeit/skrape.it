@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val ktorVersion = "1.4.1"
    val testContainersVersion = "1.14.3"
    val mockitoVersion = "2.2.0"
    val striktVersion = "0.28.0"

    implementation(project(":core"))
    implementation("io.ktor:ktor-server-test-host:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")

    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoVersion")
    testImplementation("io.strikt:strikt-core:$striktVersion")

}