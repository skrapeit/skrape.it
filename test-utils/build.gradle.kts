plugins {
    buildsrc-convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc-convention.`kotlin-multiplatform-js`
}

kotlin {
    js() {
        browser() {
            commonWebpackConfig {
                configDirectory = rootProject.projectDir.toPath().resolve("webpack.config.d").toFile()
            }
        }
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
