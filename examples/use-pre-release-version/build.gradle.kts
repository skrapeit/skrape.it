plugins {
    buildsrc.convention.`kotlin-jvm`
}

configurations.configureEach {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

dependencies {
    implementation("it.skrape:skrapeit:1.3.0-alpha.1") {
        isChanging = true
    }
}
