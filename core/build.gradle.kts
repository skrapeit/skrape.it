@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val jsoupVersion = "1.13.1"
    val htmlUnitVersion = "2.42.0"
    val kohttpVersion = "0.12.0"
    val testContainersVersion = "1.14.3"
    val wireMockVersion = "2.27.1"
    val log4jOverSlf4jVersion = "1.7.30"
    val logbackVersion = "1.2.3"
    val ktorVersion = "1.3.2"

    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion")
    implementation("io.github.rybalkinsd:kohttp:$kohttpVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

    testImplementation("com.github.tomakehurst:wiremock-jre8:$wireMockVersion")
    testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")
    testImplementation("io.ktor:ktor-client-core:$ktorVersion")
    testImplementation("io.ktor:ktor-client-apache:$ktorVersion")
}

tasks {
    test {
        finalizedBy(jacocoTestReport)
    }
}
