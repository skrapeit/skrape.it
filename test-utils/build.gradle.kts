plugins {
    buildsrc.convention.`kotlin-multiplatform`
}

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                compileOnly(projects.htmlParser)

            }
        }

        val jvmMain by getting {
            dependencies {
                api(Deps.wireMock)

                api(Deps.jUnit)
                api(Deps.strikt)
                api(Deps.Mockk.mockk)
                api(Deps.Mockk.dslJvm)

                implementation(Deps.TestContainers.testContainers)
                implementation(Deps.TestContainers.jUnit)

                implementation(Deps.restAssured)
            }
        }
    }
}
