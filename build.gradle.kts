@file:Suppress("UNUSED_VARIABLE")

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka") apply false
    id("com.github.ben-manes.versions")
    id("se.patrikerdes.use-latest-versions")
    id("com.adarshr.test-logger")
    id("io.gitlab.arturbosch.detekt")
    id("com.vanniktech.maven.publish")
}

allprojects {
    group = "it.skrape"
    version = "0-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.vanniktech.maven.publish")
    apply(plugin = "se.patrikerdes.use-latest-versions")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "com.adarshr.test-logger")

    configure<TestLoggerExtension> {
        setTheme("mocha-parallel")
        slowThreshold = 1000
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configure<DetektExtension> {
        toolVersion = "1.9.1"
        autoCorrect = true
        input = files(DEFAULT_SRC_DIR_KOTLIN)
        config = files("${rootDir}/config/detekt/detekt.yml")
    }
}

subprojects {

    dependencies {
        val junitVersion = "5.7.0"
        val striktVersion = "0.28.0"
        val mockkVersion = "1.10.2"
        testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
        testImplementation("io.strikt:strikt-core:$striktVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("io.mockk:mockk-dsl-jvm:$mockkVersion")
    }
    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions.apply {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjsr305=strict")
                apiVersion = "1.3"
                languageVersion = "1.3"
            }
        }

        withType<JacocoReport> {
            reports {
                xml.isEnabled = true
            }
        }

        withType<Test> {
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
        }

        withType<DependencyUpdatesTask> {

            gradleReleaseChannel = "current"

            rejectVersionIf {
                val isFlaggedAsNonStable = listOf("alpha", "beta", "RC", "rc", "dev").any { candidate.version.contains(it) }.not()
                val isSemanticVersion = "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
                (isFlaggedAsNonStable || isSemanticVersion).not()
            }
        }
    }

    tasks {
        val updateDependencies by creating {
            dependsOn(useLatestVersions, test)
        }
    }
}

jacoco {
    toolVersion = "0.8.5"
}

tasks {
    val jacocoTestReport by getting(JacocoReport::class) {
        dependsOn(subprojects.map { it.tasks.withType<Test>() })
        dependsOn(subprojects.map { it.tasks.withType<JacocoReport>() })
        additionalSourceDirs.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].allSource.srcDirs })
        sourceDirectories.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].allSource.srcDirs })
        classDirectories.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].output })
        executionData.setFrom(project.fileTree(".") { include("**/build/jacoco/test.exec") })
        reports {
            xml.isEnabled = true
            html.isEnabled = false
            csv.isEnabled = false
            html.destination = file("${buildDir}/reports/jacoco/html")
        }
    }

    build {
        finalizedBy(jacocoTestReport)
    }
}

dependencies {
    implementation(project(":core"))
}
