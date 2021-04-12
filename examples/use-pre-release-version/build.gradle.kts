plugins {
    kotlin("jvm")
}

repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    /*implementation("com.github.skrapeit.skrape.it:skrapeit-html-parser:master-SNAPSHOT")
    implementation("com.github.skrapeit.skrape.it:skrapeit-base-fetcher:master-SNAPSHOT")
    implementation("com.github.skrapeit.skrape.it:skrapeit-http-fetcher:master-SNAPSHOT")
    implementation("com.github.skrapeit.skrape.it:skrapeit-browser-fetcher:master-SNAPSHOT")*/
    // or use a certain commit to avoid sudden breaking changes
    // implementation("com.github.skrapeit:skrape.it:cc9b66e")
    implementation("it.skrape:skrapeit:1.0.0") // TODO: use snapshot
}
