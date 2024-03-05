plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
    buildsrc.convention.kover
}

dependencies {
    api(projects.fetcher.baseFetcher)
    api(Deps.htmlUnit) {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    api(Deps.logback)
    api(Deps.log4jOverSlf4j)
    api("io.fluidsonic.mirror:cdt-java-client:4.0.0-fluidsonic-1")

    testImplementation(projects.testUtils)
}
