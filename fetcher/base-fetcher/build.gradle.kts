plugins {
    id("kotlin-library-convention")
}

dependencies {
    implementation(projects.dsl)
    implementation(libs.kotlin.reflect) {
        because("to support Result#extractIt by creating instance of a class")
    }
    api(libs.kotlinx.coroutines.jdk8)

    testImplementation(projects.testUtils)
    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.client.apache)
}
