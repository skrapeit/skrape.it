@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

val springVersion = "5.2.9.RELEASE"

dependencies {
    provided(project(":core"))
    provided("org.springframework:spring-webmvc:$springVersion")
    provided("org.springframework:spring-test:$springVersion")
    provided("javax.servlet:javax.servlet-api:4.0.1")
}

fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}