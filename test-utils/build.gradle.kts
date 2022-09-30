plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
    buildsrc.convention.`kotlin-multiplatform-js-node`
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly(projects.htmlParser)
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
