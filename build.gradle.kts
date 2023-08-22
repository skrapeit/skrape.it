plugins {
    buildsrc.convention.base
    buildsrc.convention.kover
    buildsrc.convention.`kotlin-jvm`
    buildsrc.convention.`publish-jvm`
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
    api(projects.fetcher.httpFetcher)
    api(projects.dsl)
    api(projects.htmlParser)

    kover(projects.assertions)
    kover(projects.fetcher.asyncFetcher)
    kover(projects.fetcher.baseFetcher)
    kover(projects.fetcher.browserFetcher)
    kover(projects.fetcher.httpFetcher)
    kover(projects.dsl)
    kover(projects.htmlParser)
    kover(projects.ktorExtension)
    kover(projects.mockMvcExtension)
    kover(projects.testUtils)
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
