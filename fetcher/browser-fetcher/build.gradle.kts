plugins {
    id("kotlin-library-convention")
}

dependencies {
    api(projects.fetcher.baseFetcher)
    api(libs.htmlunit.android) {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    api(libs.logback.classic)
    api(libs.log4j.over.slf4j)

    testImplementation(projects.testUtils)
}
