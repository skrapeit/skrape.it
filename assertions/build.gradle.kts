plugins {
    buildsrc.convention.`kotlin-multiplatform`
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
    }
}
