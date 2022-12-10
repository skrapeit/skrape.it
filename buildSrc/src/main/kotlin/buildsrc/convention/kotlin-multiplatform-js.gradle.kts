package buildsrc.convention

import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest

plugins {
    id("buildsrc.convention.kotlin-multiplatform")
}

val combineTask = tasks.register<Copy>("jsMergeResources") {
    dependsOn("jsProcessResources")
    dependsOn("jsTestProcessResources")
    from(
        layout.buildDirectory.dir("processedResources/js/main"),
        layout.buildDirectory.dir("processedResources/js/test")
    )
    into(
        layout.buildDirectory.dir("processedResources/js/combined")
    )
}

tasks.withType<KotlinJsTest>() {
    dependsOn(combineTask)
}