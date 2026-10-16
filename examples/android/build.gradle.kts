buildscript {
    dependencies {
        // Upgrade AGP's built-in Kotlin compiler to the same version as the published library.
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.composeCompiler) apply false
}
