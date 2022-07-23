plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`maven-publishing`
}

dependencies {
    api(projects.fetcher.baseFetcher)
    implementation(Deps.Ktor.client)
    implementation(Deps.Ktor.clientApache)
    implementation(Deps.Ktor.clientLogging)
    implementation(Deps.logback)
    implementation(Deps.log4jOverSlf4j)

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
