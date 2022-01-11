@file:Suppress("UNUSED_VARIABLE", "LocalVariableName")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN
import kotlinx.kover.api.KoverTaskExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    signing
    kotlin("jvm")
    id("org.jetbrains.dokka") apply false
    id("org.jetbrains.kotlinx.kover")
    id("com.github.ben-manes.versions")
    id("se.patrikerdes.use-latest-versions")
    id("com.adarshr.test-logger")
    id("io.gitlab.arturbosch.detekt")
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    val release_version: String by project
    version = release_version
    group = "it.skrape"

    repositories {
        mavenCentral()
    }

    apply(plugin = "com.github.ben-manes.versions")
    apply(plugin = "se.patrikerdes.use-latest-versions")

    apply(plugin = "com.adarshr.test-logger")
    testlogger {
        setTheme("mocha-parallel")
        slowThreshold = 1000
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        toolVersion = "1.19.0"
        autoCorrect = true
        buildUponDefaultConfig = true
        source = files(DEFAULT_SRC_DIR_KOTLIN)
        config = files("$rootDir/detekt.yml")
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    kotlin {
        explicitApi = ExplicitApiMode.Strict
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
        withJavadocJar()
        withSourcesJar()
    }

    val includeToPublishing = listOf(
        "assertions",
        "base-fetcher",
        "dsl",
        "http-fetcher",
        "async-fetcher",
        "browser-fetcher",
        "html-parser",
        "ktor-extension",
        "mock-mvc-extension",
        "skrapeit"
    )
    if (this.name in includeToPublishing) {
        apply(plugin = "org.jetbrains.dokka")
        apply(plugin = "maven-publish")
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    artifactId = if (rootProject.name == project.name) rootProject.name else "${rootProject.name}-${project.name}"
                    from(components["java"])
                    pom {
                        name.set("skrape{it}")
                        description.set("A Kotlin-based testing/scraping/parsing library providing the ability to analyze and extract data from HTML (server & client-side rendered). It places particular emphasis on ease of use and a high level of readability by providing an intuitive DSL. First and foremost it aims to be a testing lib, but it can also be used to scrape websites in a convenient fashion.")
                        url.set("https://docs.skrape.it")
                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("https://opensource.org/licenses/MIT")
                            }
                        }
                        developers {
                            developer {
                                id.set("christian-draeger")
                                name.set("Christian Dräger")
                            }
                        }
                        scm {
                            connection.set("scm:git:git://github.com/skrapeit/skrape.it.git")
                            developerConnection.set("scm:git:ssh://github.com:skrapeit/skrape.it.git")
                            url.set("https://github.com/skrapeit/skrape.it/tree/master")
                        }
                    }
                }
            }
        }

        apply(plugin = "signing")
        signing {
            sign(publishing.publications["mavenJava"])

            val signingKeyId: String? by project
            val signingKey: String? by project
            val signingPassword: String? by project
            useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        }
    }
}

subprojects {

    dependencies {
        testImplementation(Deps.jUnit)
        testImplementation(Deps.strikt)
        testImplementation(Deps.Mockk.mockk)
        testImplementation(Deps.Mockk.dslJvm)
    }
    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions.apply {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjsr305=strict")
                apiVersion = "1.4"
                languageVersion = "1.4"
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
                events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
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
                val isFlaggedAsNonStable =
                    listOf("alpha", "beta", "RC", "rc", "dev", "M1", "M2", "M3").any { candidate.version.contains(it) }
                        .not()
                val isSemanticVersion = "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
                (isFlaggedAsNonStable || isSemanticVersion).not()
            }
        }

        val updateDependencies by creating {
            dependsOn(useLatestVersions, test)
        }
    }
}

tasks {
    test {
        extensions.configure(KoverTaskExtension::class) {
            excludes = listOf("com.example.subpackage.*")
        }
        finalizedBy(koverReport, koverCollectProjectsReports)
    }
}

kover {
    coverageEngine.set(kotlinx.kover.api.CoverageEngine.JACOCO)
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

dependencies {
    api(projects.assertions)
    api(projects.asyncFetcher)
    api(projects.baseFetcher)
    api(projects.browserFetcher)
    api(projects.dsl)
    api(projects.httpFetcher)
    api(projects.htmlParser)
}
