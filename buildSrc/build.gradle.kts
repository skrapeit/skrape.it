plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.9.10"
}

// set the versions of Gradle plugins that the subprojects will use here
val kotlinVersion = "1.9.10"

val detektPlugin = "1.23.4"
val gradleNexusPublishPlugin = "1.3.0"
val gradleTestLoggerPlugin = "4.0.0"
val gradleVersionsPlugin = "0.50.0"
val kotlinDokkaPlugin = "1.9.10"
val kotlinxKoverPlugin = "0.7.5"
val useLatestVersionsPlugin = "0.2.18"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    implementation("org.jetbrains.kotlin:kotlin-serialization")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektPlugin")
    implementation("io.github.gradle-nexus:publish-plugin:$gradleNexusPublishPlugin")
    implementation("com.adarshr:gradle-test-logger-plugin:$gradleTestLoggerPlugin")
    implementation("com.github.ben-manes:gradle-versions-plugin:$gradleVersionsPlugin")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$kotlinDokkaPlugin")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:$kotlinxKoverPlugin")
    implementation("se.patrikerdes:gradle-use-latest-versions-plugin:$useLatestVersionsPlugin")
}
