plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    compileOnly(projects.htmlParser)

    api(Deps.wireMock)

    implementation(Deps.TestContainers.testContainers)
    implementation(Deps.TestContainers.jUnit)

    testImplementation(Deps.restAssured)
}
