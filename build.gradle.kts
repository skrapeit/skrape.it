plugins {
    buildsrc.convention.base
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`

    id("org.jetbrains.kotlinx.kover")

    id("io.github.gradle-nexus.publish-plugin")
}

@Suppress("PropertyName")
val release_version: String by project
version = release_version
group = "it.skrape"

dependencies {
    api(projects.assertions)
    api(projects.fetcher.asyncFetcher)
    api(projects.fetcher.baseFetcher)
    api(projects.fetcher.browserFetcher)
    api(projects.dsl)
    api(projects.fetcher.httpFetcher)
    api(projects.htmlParser)
}

tasks.withType<Test>().configureEach {
    finalizedBy(tasks.koverReport, tasks.koverCollectReports)
}

kover {
    coverageEngine.set(kotlinx.kover.api.CoverageEngine.INTELLIJ)
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

tasks.wrapper {
    gradleVersion = "7.5"
    distributionType = Wrapper.DistributionType.ALL
}
