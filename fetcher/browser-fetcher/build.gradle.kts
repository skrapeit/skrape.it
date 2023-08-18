plugins {
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
}

dependencies {
    api(projects.fetcher.baseFetcher)
    api(Deps.htmlUnit) {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    api(Deps.logback)
    api(Deps.log4jOverSlf4j)

    testImplementation(projects.testUtils)
}
