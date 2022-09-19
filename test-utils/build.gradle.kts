plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly(projects.htmlParser)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Deps.TestContainers.testContainers)
                implementation(Deps.TestContainers.jUnit)
                implementation(Deps.wireMock)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.restAssured)
            }
        }
    }
}
