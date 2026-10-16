pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        exclusiveContent {
            forRepository {
                maven {
                    name = "skrapeitLocal"
                    url = uri(providers.gradleProperty("skrapeitRepository").getOrElse("../../build/maven-smoke-test"))
                }
            }
            filter { includeGroup("it.skrape") }
        }
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") { from(files("../../gradle/libs.versions.toml")) }
    }
}

rootProject.name = "skrapeit-android-example"
include(":app")
