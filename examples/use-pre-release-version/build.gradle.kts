plugins {
    buildsrc.convention.`kotlin-jvm`
}

configurations.configureEach {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}


repositories {
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}
dependencies {
    // not found it.skrape:skrapeit:0-SNAPHOT exchanged with current version to ensure it runs until this is fixed
    implementation("it.skrape:skrapeit:1.3.0-alpha.1") { isChanging = true }
}
