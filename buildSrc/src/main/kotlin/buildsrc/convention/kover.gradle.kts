package buildsrc.convention

plugins {
    base
    id("org.jetbrains.kotlinx.kover")
}

tasks.check {
    dependsOn(tasks.koverXmlReport)
}
