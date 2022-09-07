plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.kover
    buildsrc.convention.`publish-kmp`
}

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.htmlParser)
                api(projects.dsl)
                api(projects.fetcher.baseFetcher)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(Deps.jUnit)
                implementation(Deps.strikt)
                implementation(Deps.Mockk.mockk)
                implementation(Deps.Mockk.dslJvm)
            }
        }
    }
}
