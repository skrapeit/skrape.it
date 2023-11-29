package buildsrc.convention

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    base
    id("com.github.ben-manes.versions")
    id("se.patrikerdes.use-latest-versions")
}

val updateDependencies by tasks.registering {
    group = LifecycleBasePlugin.BUILD_GROUP
    dependsOn(tasks.useLatestVersions)
    dependsOn(tasks.withType<Test>())
}

tasks.check {
    mustRunAfter(updateDependencies)
}

tasks.withType<DependencyUpdatesTask>().configureEach {

    gradleReleaseChannel = "current"

    rejectVersionIf {
        val isFlaggedAsNonStable =
            listOf(
                "alpha",
                "beta",
                "RC",
                "rc",
                "dev",
                "M1",
                "M2",
                "M3",
            ).any { candidate.version.contains(it) }
                .not()
        val isSemanticVersion = "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
        (isFlaggedAsNonStable || isSemanticVersion).not()
    }
}
