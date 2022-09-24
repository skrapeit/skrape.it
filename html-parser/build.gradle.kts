plugins {
    kotlin("multiplatform")
}

/*java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}*/

kotlin {
    js() {
        browser {
            testTask {
                environment("PROJECT_PATH", projectDir.absolutePath)
                useKarma {
                    useFirefoxHeadless()
                    useChromeHeadless()
                    useSourceMapSupport()
                }
            }
        }
        nodejs {
            testTask {
                environment("PROJECT_PATH", projectDir.absolutePath)
                useMocha {

                }
            }
        }
        useCommonJs()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Ktor.client)
                implementation(kotlin("stdlib"))
                implementation(projects.dsl)
                implementation("com.benasher44:uuid:0.4.1")
                api(projects.baseFetcher)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
                implementation(projects.testUtils)
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-common:0.18.0")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(Deps.Ktor.clientJS)
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:0.18.0")
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
                api(projects.browserFetcher)
                api(Deps.jsoup)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.wireMock)
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB:0.18.0")
            }
        }
    }
}

//"jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))
