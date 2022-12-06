plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`kotlin-multiplatform-jvm`
}

kotlin {
    sourceSets {
        val jvmTest by getting {
            dependencies {
                implementation(Deps.jetbrainsAnnotations)
                implementation(projects.testUtils)
                implementation(projects.htmlParser)
                implementation(projects.assertions)
                implementation(projects.fetcher)
            }
        }
    }
}
