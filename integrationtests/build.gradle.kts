plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
    buildsrc.convention.`kotlin-multiplatform-js-node`
    buildsrc.convention.`kotlin-multiplatform-js-web`
}

kotlin {
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(projects.testUtils)
                implementation(projects.htmlParser)
                implementation(projects.assertions)
                implementation(projects.fetcher)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.jetbrainsAnnotations)
            }
        }
    }
}
