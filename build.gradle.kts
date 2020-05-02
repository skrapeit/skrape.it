@file:Suppress("UNUSED_VARIABLE")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    kotlin("jvm") version "1.3.61"
    jacoco
    id("org.jetbrains.dokka") version "0.10.1"
    id("se.patrikerdes.use-latest-versions") version "0.2.13"
    id("com.github.ben-manes.versions") version "0.28.0"
    id("com.adarshr.test-logger") version "2.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.5.1"
    id("com.vanniktech.maven.publish") version "0.9.0"
}

val isIdea = System.getProperty("idea.version") != null

testlogger {
    setTheme(if (isIdea) "plain" else "mocha")
    slowThreshold = 1000
}

repositories {
    jcenter()
}

dependencies {
    val kotlinVersion = "1.3.61"
    val jsoupVersion = "1.12.2"
    val htmlUnitVersion = "2.37.0"
    val striktVersion = "0.24.0"
    val kohttpVersion = "0.11.1"

    val junitVersion = "5.6.0"
    val testContainersVersion = "1.12.4"
    val wireMockVersion = "2.26.0"
    val mockkVersion = "1.9.3"
    val log4jOverSlf4jVersion = "1.7.30"
    val logbackVersion = "1.2.3"

    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion")
    implementation("io.github.rybalkinsd:kohttp:$kohttpVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.strikt:strikt-core:$striktVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.mockk:mockk-dsl-jvm:$mockkVersion")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wireMockVersion")
    testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

detekt {
    toolVersion = "1.1.1"
    input = files(DetektExtension.DEFAULT_SRC_DIR_KOTLIN)
    config = files("$projectDir/src/test/resources/detekt.yml")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
        }
    }

    test {
        shouldRunAfter(useLatestVersions)
        dependsOn(detekt)
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        finalizedBy(jacocoTestReport)
    }

    withType<DependencyUpdatesTask> {

        gradleReleaseChannel = "current"

        rejectVersionIf {
            val isFlaggedAsNonStable = listOf("alpha", "beta", "RC", "rc").any { candidate.version.contains(it) }.not()
            val isSemanticVersion =  "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
            (isFlaggedAsNonStable || isSemanticVersion).not()
        }
    }

    val updateDependencies by creating {
        dependsOn(useLatestVersions, test)
    }
}
