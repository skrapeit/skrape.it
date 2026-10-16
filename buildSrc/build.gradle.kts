plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("com.vanniktech:gradle-maven-publish-plugin:${libs.versions.mavenPublishPlugin.get()}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt.get()}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${libs.versions.dokka.get()}")
    implementation("com.adarshr:gradle-test-logger-plugin:${libs.versions.testLogger.get()}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${libs.versions.versionsPlugin.get()}")
    implementation("se.ascp.gradle:gradle-versions-filter:${libs.versions.versionsFilterPlugin.get()}")
    implementation("nl.littlerobots.vcu:plugin:${libs.versions.versionCatalogUpdate.get()}")
}

kotlin {
    jvmToolchain(17)
}
