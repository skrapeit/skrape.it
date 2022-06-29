plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                compileOnly(projects.htmlParser)

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
