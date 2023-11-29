package buildsrc.convention

plugins {
    base
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    toolVersion = "1.23.4"
    autoCorrect = true
    buildUponDefaultConfig = true
    parallel = true
    config.setFrom("$rootDir/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.check {
    dependsOn(tasks.detekt)
}
