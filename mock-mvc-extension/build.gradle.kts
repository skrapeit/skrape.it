plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
}

dependencies {
    provided(projects.htmlParser)
    provided(projects.assertions)
    provided(Deps.Spring.webMvc)
    provided(Deps.Spring.test)
    provided(Deps.javaxServlet)
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
