plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmTest by getting {
            dependencies {
                implementation(Deps.jetbrainsAnnotations)
                implementation(Deps.wireMock)
                implementation(project(":test-utils"))
                implementation(project(":html-parser"))
                implementation(project(":assertions"))
                implementation(project(":base-fetcher"))
                implementation(project(":http-fetcher"))
                implementation(project(":browser-fetcher"))
                implementation(project(":async-fetcher"))
            }
        }
    }
}
