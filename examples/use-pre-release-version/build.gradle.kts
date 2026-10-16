plugins {
    id("kotlin-jvm-convention")
}

configurations.configureEach {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

dependencies {
    // Use an existing pre-release so a clean build does not depend on a snapshot upload.
    implementation("it.skrape:skrapeit:1.3.0-alpha.1")
}
