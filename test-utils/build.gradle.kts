plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly(project(":html-parser"))

    val testContainersVersion = "1.15.2"
    implementation("org.testcontainers:testcontainers:$testContainersVersion")
    implementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    implementation("com.github.tomakehurst:wiremock-jre8:2.27.2")

    val restAssuredVersion = "4.3.3"
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:rest-assured-all:$restAssuredVersion")
}
