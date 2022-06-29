plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("it.skrape:skrapeit:+")
            }
        }
    }
}
