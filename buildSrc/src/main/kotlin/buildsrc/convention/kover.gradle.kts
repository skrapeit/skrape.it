package buildsrc.convention

import kotlinx.kover.api.IntellijEngine

plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    // https://search.maven.org/artifact/org.jetbrains.intellij.deps/intellij-coverage-agent
    engine.set(IntellijEngine("1.0.681"))
}
