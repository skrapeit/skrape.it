plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
