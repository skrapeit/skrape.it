plugins {
    buildsrc.convention.`kotlin-multiplatform`
}

//java {
//    registerFeature("jsExecution") {
//        usingSourceSet(sourceSets["main"])
//    }
//}
//
//dependencies {
//    implementation(projects.dsl)
//    api(projects.fetcher.baseFetcher)
//    api(Deps.jsoup)
//
//    "jsExecutionImplementation"(projects.fetcher.browserFetcher)
//
//    testImplementation(projects.testUtils)
//}

kotlin {
    jvm {}
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.dsl)
                api(projects.fetcher.baseFetcher)
                api(projects.fetcher.browserFetcher)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(Deps.jsoup)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(projects.testUtils)
            }
        }
    }
}
