package buildsrc.convention

import kotlinx.kover.api.IntellijEngine

plugins {
    base
    id("org.jetbrains.kotlinx.kover")
    id("com.adarshr.test-logger")
    id("buildsrc.convention.version-management")
}

repositories {
    mavenCentral()
}

description =
    "Common build config that can be applied to all subprojects. This should typically be language-independent."

if (project != rootProject) {
    group = rootProject.group
    version = rootProject.version
}

testlogger {
    setTheme("mocha-parallel")
    slowThreshold = 1000
    showStandardStreams = false
}

kover {
    // https://search.maven.org/artifact/org.jetbrains.intellij.deps/intellij-coverage-agent
    engine.set(IntellijEngine("1.0.684"))
}
