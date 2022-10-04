plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
}

dependencies {
    api(projects.fetcher.baseFetcher)
    api(Deps.htmlUnit) {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    val log4jOverSlf4jVersion = "2.0.3"
    val logbackVersion = "1.3.3"
    api("ch.qos.logback:logback-classic:$logbackVersion")
    api("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")

    testImplementation(projects.testUtils)
}
