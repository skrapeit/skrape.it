plugins {
    kotlin("multiplatform")
}

/*java {
    registerFeature("jsExecution") {
        usingSourceSet(sourceSets["main"])
    }
}*/

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.dsl)
                api(projects.browserFetcher)
                api(projects.baseFetcher)
                api(Deps.jsoup)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.wireMock)
                implementation(projects.testUtils)
            }
        }
    }
}

//"jsExecutionImplementation"(project(path = ":browser-fetcher", configuration = "default"))
