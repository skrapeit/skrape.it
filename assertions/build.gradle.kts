plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(projects.htmlParser)
                api(projects.dsl)
                api(projects.baseFetcher)
            }

        }
    }
}