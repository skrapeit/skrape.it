plugins {
    idea
    `kotlin-dsl`
    kotlin("jvm") version "1.6.21"
}

// set the versions of Gradle plugins that the subprojects will use here
val kotlinVersion: String = "1.6.21"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    implementation("org.jetbrains.kotlin:kotlin-serialization")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("se.patrikerdes:gradle-use-latest-versions-plugin:0.2.18")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
    implementation("org.jetbrains.kotlinx:kover:0.5.0")
    implementation("com.adarshr:gradle-test-logger-plugin:3.2.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.7.10")
}
