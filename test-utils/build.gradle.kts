plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
    buildsrc.convention.`kotlin-multiplatform-js-node`
    kotlin("plugin.serialization")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly(projects.htmlParser)
                implementation(Deps.KotlinX.serialization)
                implementation(Deps.Ktor.client)
                implementation(Deps.Ktor.clientContentNegotiation)
                implementation(Deps.Ktor.serializationJson)
                implementation(Deps.uuid)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientJS)
                implementation(npm("testcontainers","8.15.0"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientCIO)
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
