plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-web`
    buildsrc.convention.`kotlin-multiplatform-js-node`
    buildsrc.convention.`publish-multiplatform`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.htmlParser)
                api(projects.dsl)
                api(projects.fetcher)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Deps.Kotest.datatest)
            }
        }
    }
}