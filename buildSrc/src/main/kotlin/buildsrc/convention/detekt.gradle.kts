package buildsrc.convention

import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN

plugins {
    base
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    toolVersion = "1.19.0"
    autoCorrect = true
    buildUponDefaultConfig = true
    source = files(DEFAULT_SRC_DIR_KOTLIN)
    config = files("$rootDir/detekt.yml")
}

tasks.check {
    dependsOn(tasks.detekt)
}
