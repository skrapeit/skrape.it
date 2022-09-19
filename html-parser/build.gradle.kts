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
                useKarma {
                    useFirefoxHeadless()
                    useChromeHeadless()
                    useSourceMapSupport()
                }
            }
        }
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
                implementation(projects.testUtils)
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-common:0.18.0")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:0.18.0")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("js-beautify","1.14.4"))
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
