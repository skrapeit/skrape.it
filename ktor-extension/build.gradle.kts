plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val ktorVersion = "1.4.1"

    provided(project(":html-parser"))
    provided("io.ktor:ktor-server-test-host:$ktorVersion")
    provided("io.ktor:ktor-server-netty:$ktorVersion")
    provided("io.ktor:ktor-freemarker:$ktorVersion")
    provided("io.ktor:ktor-locations:$ktorVersion")
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
