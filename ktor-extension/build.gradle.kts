@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val ktorVersion = "1.4.1"

    provided(project(":core"))
    provided("io.ktor:ktor-server-test-host:$ktorVersion")
    provided("io.ktor:ktor-server-netty:$ktorVersion")
    provided("io.ktor:ktor-freemarker:$ktorVersion")
    provided("io.ktor:ktor-locations:$ktorVersion")
}

fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
