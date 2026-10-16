plugins {
    id("kotlin-library-convention")
}

dependencies {
    provided(projects.htmlParser)
    provided(projects.assertions)
    provided(libs.spring.webmvc)
    provided(libs.spring.test)
    provided(libs.javax.servlet.api)
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun DependencyHandlerScope.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    testCompileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
