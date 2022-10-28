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
    // lazily access koverMergedReport task because it doesn't have a specific DSL accessor or type
    val koverMergedReportTask = tasks.matching { it.name == kotlinx.kover.api.KoverNames.MERGED_REPORT_TASK_NAME }
    finalizedBy(tasks.koverReport, koverMergedReportTask)
}

koverMerged {
    enable()

    filters {
        projects {
            excludes += listOf(
                ":examples:scraping",
                ":examples:use-pre-release-version",
                ":fetcher:async-fetcher",
                ":fetcher:base-fetcher",
                ":fetcher:browser-fetcher",
                ":fetcher:http-fetcher"
            )
        }
    }
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
