plugins {
    buildsrc.convention.`kotlin-jvm`
}

configurations.configureEach {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

dependencies {
    implementation("it.skrape:skrapeit:0-SNAPSHOT") {
        isChanging = true
    }
}
