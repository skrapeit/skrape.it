plugins {
    buildsrc.convention.`kotlin-jvm`
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        mavenContent { snapshotsOnly() }
    }
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

dependencies {
    implementation("it.skrape:skrapeit:0-SNAPSHOT") {
        isChanging = true
    }
}
