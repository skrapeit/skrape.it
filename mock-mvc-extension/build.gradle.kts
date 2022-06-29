@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

val kotlin_version: String by project

plugins {
    kotlin("multiplatform")
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

dependencies {

}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun KotlinDependencyHandler.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
