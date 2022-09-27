plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatfomr-jvm`
    buildsrc.convention.`publish-jvm`
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