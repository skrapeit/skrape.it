plugins {
    kotlin("jvm")
}

repositories {
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}

dependencies {
    implementation("it.skrape:skrapeit:0-SNAPSHOT")
}
