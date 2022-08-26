plugins {
    buildsrc.convention.`kotlin-multiplatform`
    buildsrc.convention.`publish-kmp`

    id("org.jetbrains.kotlinx.kover")

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
    gradleVersion = "7.5.1"
    distributionType = Wrapper.DistributionType.ALL
}
