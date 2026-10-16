plugins {
    id("kotlin-library-convention")
}

dependencies {
    provided(projects.htmlParser)
    provided(libs.ktor.server.test.host)
    provided(libs.ktor.server.netty)
    provided(libs.ktor.server.freemarker)
    provided(libs.ktor.server.resources)
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
