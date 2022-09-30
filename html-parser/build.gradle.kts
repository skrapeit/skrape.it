plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
    buildsrc.convention.`kotlin-multiplatform-js-node`
    buildsrc.convention.`publish-multiplatform`
}

/*java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}*/

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Ktor.client)
                implementation(projects.dsl)
                implementation("com.benasher44:uuid:0.4.1")
                api(projects.fetcher.baseFetcher)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
                implementation(projects.testUtils)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(Deps.Ktor.clientJS)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("jsdom","20.0.0"))
                implementation(npm("buffer", "6.0.3"))
                implementation(npm("process","0.11.10"))
                implementation(npm("stream-browserify", "3.0.0"))
                implementation(npm("url","0.11.0"))
                implementation(npm("linkedom","0.14.16"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(projects.dsl)
                api(projects.fetcher.browserFetcher)
                api(Deps.jsoup)
            }
        }
    }
}

//"jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))
