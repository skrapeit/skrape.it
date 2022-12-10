plugins {
    `kotlin-dsl`
    //Convention plugins require 1.6.21
    //kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        mavenContent { snapshotsOnly() }
    }
}

// set the versions of Gradle plugins that the subprojects will use here
val kotlinVersion = "1.7.21"

val detektPlugin = "1.19.0"
val gradleNexusPublishPlugin = "1.1.0"
val gradleTestLoggerPlugin = "3.1.0"
val gradleVersionsPlugin = "0.39.0"
val kotlinDokkaPlugin = "1.7.20"
val kotlinxKoverPlugin = "0.6.1"
val useLatestVersionsPlugin = "0.2.18"
val kotestVersion = "5.5.4"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    implementation("org.jetbrains.kotlin:kotlin-serialization")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("io.kotest:kotest-framework-multiplatform-plugin-gradle:$kotestVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektPlugin")
    implementation("io.github.gradle-nexus:publish-plugin:$gradleNexusPublishPlugin")
    implementation("com.adarshr:gradle-test-logger-plugin:$gradleTestLoggerPlugin")
    implementation("com.github.ben-manes:gradle-versions-plugin:$gradleVersionsPlugin")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$kotlinDokkaPlugin")
    implementation("org.jetbrains.kotlinx:kover:$kotlinxKoverPlugin")
    implementation("se.patrikerdes:gradle-use-latest-versions-plugin:$useLatestVersionsPlugin")
}
