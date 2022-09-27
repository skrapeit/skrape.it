plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildscr.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`publish-jvm`
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                provided(projects.htmlParser)
                provided(projects.assertions)
                provided(Deps.Spring.webMvc)
                provided(Deps.Spring.test)
                provided(Deps.javaxServlet)
            }
        }
        val jvmTest by getting {
            dependencies {
                provided(projects.htmlParser)
                provided(projects.assertions)
                provided(Deps.Spring.webMvc)
                provided(Deps.Spring.test)
                provided(Deps.javaxServlet)
            }
        }
    }
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun KotlinDependencyHandler.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
