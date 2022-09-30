plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`publish-multiplatform`
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(projects.htmlParser)
                api(projects.dsl)
                api(projects.fetcher.baseFetcher)
            }

        }
    }
}