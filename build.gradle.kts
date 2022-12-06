plugins {
    buildsrc.convention.base
    buildsrc.convention.`publish-multiplatform`

    id("org.jetbrains.kotlinx.kover")

    id("io.github.gradle-nexus.publish-plugin")
}

@Suppress("PropertyName")
val release_version: String by project
version = release_version
group = "it.skrape"


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
                ":fetcher"
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
