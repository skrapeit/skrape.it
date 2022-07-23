plugins {
    buildsrc.convention.`kotlin-jvm`
}

dependencies {
    api(projects.fetcher.baseFetcher)
    api(Deps.htmlUnit) {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    val log4jOverSlf4jVersion = "1.7.36"
    val logbackVersion = "1.2.11"
    api("ch.qos.logback:logback-classic:$logbackVersion")
    api("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
