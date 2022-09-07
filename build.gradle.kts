import kotlinx.kover.api.KoverNames

plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kmp`

    buildsrc.convention.kover

    id("io.github.gradle-nexus.publish-plugin")
}

@Suppress("PropertyName")
val release_version: String by project
version = release_version
group = "it.skrape"

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.assertions)
                api(projects.fetcher.asyncFetcher)
                api(projects.fetcher.baseFetcher)
                api(projects.fetcher.browserFetcher)
                api(projects.dsl)
                api(projects.fetcher.httpFetcher)
                api(projects.htmlParser)
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    finalizedBy(tasks.koverReport)
    // lazily access koverMergedReport task because it doesn't have a specific DSL accessor or type
    val koverMergedReportTask = tasks.matching { it.name == KoverNames.MERGED_REPORT_TASK_NAME }
    finalizedBy(koverMergedReportTask)
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
                ":fetcher:http-fetcher",

                // Intermediate projects, without a build.gradle.kts.
                // (These exclusions can be removed in a future Kover release https://github.com/Kotlin/kotlinx-kover/issues/222)
                ":examples",
                ":fetcher",
                ":test-utils",
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
    gradleVersion = "7.5.1"
    distributionType = Wrapper.DistributionType.ALL
}
