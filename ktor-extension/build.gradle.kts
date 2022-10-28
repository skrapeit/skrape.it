import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`publish-multiplatform`
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                provided(projects.htmlParser)
                provided(Deps.Ktor.serverTestHost)
                provided(Deps.Ktor.serverNetty)
                provided(Deps.Ktor.freemarker)
                provided(Deps.Ktor.locations)
            }
        }
        val jvmTest by getting {
            dependencies {
                provided(projects.htmlParser)
                provided(Deps.Ktor.serverTestHost)
                provided(Deps.Ktor.serverNetty)
                provided(Deps.Ktor.freemarker)
                provided(Deps.Ktor.locations)
            }
        }
    }
}

// TODO: use https://github.com/nebula-plugins/gradle-extra-configurations-plugin to get provided configuration in gradle
fun KotlinDependencyHandler.provided(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
