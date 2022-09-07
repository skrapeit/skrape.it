package buildsrc.convention

plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    // https://search.maven.org/artifact/org.jetbrains.intellij.deps/intellij-coverage-agent
    engine.set(kotlinx.kover.api.IntellijEngine("1.0.681"))
}
