@file:Suppress("UNUSED_VARIABLE")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    kotlin("jvm") version "1.4.0"
    jacoco
    id("org.jetbrains.dokka") version "0.10.1"
    id("se.patrikerdes.use-latest-versions") version "0.2.14"
    id("com.github.ben-manes.versions") version "0.29.0"
    id("com.adarshr.test-logger") version "2.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
    id("com.vanniktech.maven.publish") version "0.12.0"
}

val isIdea = System.getProperty("idea.version") != null

testlogger {
    setTheme(if (isIdea) "plain" else "mocha-parallel")
    slowThreshold = 1000
}

repositories {
    jcenter()
}

dependencies {
    val kotlinVersion = "1.4.0"
    val jsoupVersion = "1.13.1"
    val htmlUnitVersion = "2.42.0"
    val striktVersion = "0.26.1"
    val kohttpVersion = "0.12.0"

    val junitVersion = "5.6.2"
    val testContainersVersion = "1.14.3"
    val wireMockVersion = "2.27.1"
    val mockkVersion = "1.10.0"
    val log4jOverSlf4jVersion = "1.7.30"
    val logbackVersion = "1.2.3"

    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion")
    implementation("io.github.rybalkinsd:kohttp:$kohttpVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

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
    toolVersion = "1.9.1"
    autoCorrect = true
    input = files(DetektExtension.DEFAULT_SRC_DIR_KOTLIN)
    config = files("$projectDir/src/test/resources/detekt.yml")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.apply {
            jvmTarget = "1.8"
            apiVersion = "1.3"
            languageVersion = "1.3"
        }
    }

    compileTestKotlin {
        kotlinOptions.apply {
            jvmTarget = "1.8"
            apiVersion = "1.3"
            languageVersion = "1.3"
        }
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
        systemProperties = mapOf(
                "junit.jupiter.execution.parallel.enabled" to true,
                "junit.jupiter.execution.parallel.mode.default" to "concurrent",
                "junit.jupiter.execution.parallel.mode.classes.default" to "concurrent"
        )
        finalizedBy(jacocoTestReport)
    }

    withType<DependencyUpdatesTask> {

        gradleReleaseChannel = "current"

        rejectVersionIf {
            val isFlaggedAsNonStable = listOf("alpha", "beta", "RC", "rc", "dev").any { candidate.version.contains(it) }.not()
            val isSemanticVersion = "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
            (isFlaggedAsNonStable || isSemanticVersion).not()
        }
    }

    val updateDependencies by creating {
        dependsOn(useLatestVersions, test)
    }
}
