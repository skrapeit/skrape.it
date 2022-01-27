plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly(projects.htmlParser)

    implementation(Deps.TestContainers.testContainers)
    implementation(Deps.TestContainers.jUnit)
    implementation(Deps.wireMock)

    testImplementation(Deps.restAssured)
}
