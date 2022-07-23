package buildsrc.convention

import Deps
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}

dependencies {
    testImplementation(Deps.jUnit)
    testImplementation(Deps.strikt)
    testImplementation(Deps.Mockk.mockk)
    testImplementation(Deps.Mockk.dslJvm)
}

kotlin {
    explicitApi()
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of("8"))
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.apply {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xjsr305=strict")
        apiVersion = "1.4"
        languageVersion = "1.4"
    }
}

tasks.withType<Test>().configureEach {
//  shouldRunAfter(useLatestVersions)
//  dependsOn(detekt)
    useJUnitPlatform()
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to true,
        "junit.jupiter.execution.parallel.mode.default" to "concurrent",
        "junit.jupiter.execution.parallel.mode.classes.default" to "concurrent"
    )
}
