plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
//    maven {
//        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
//    }
}

dependencies {
//    implementation("it.skrape:skrapeit:0-SNAPSHOT") {
//        isChanging = true
//    }
    implementation("it.skrape:skrapeit:1.0.0")
}
