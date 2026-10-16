import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("version-management-convention")
    id("kotlin-library-convention")
}

dependencies {
    api(projects.assertions)
    api(projects.fetcher.asyncFetcher)
    api(projects.fetcher.baseFetcher)
    api(projects.fetcher.browserFetcher)
    api(projects.fetcher.httpFetcher)
    api(projects.dsl)
    api(projects.htmlParser)

    dokka(projects.assertions)
    dokka(projects.fetcher.asyncFetcher)
    dokka(projects.fetcher.baseFetcher)
    dokka(projects.fetcher.browserFetcher)
    dokka(projects.fetcher.httpFetcher)
    dokka(projects.dsl)
    dokka(projects.htmlParser)
    dokka(projects.ktorExtension)
    dokka(projects.mockMvcExtension)
}

tasks {
    val detektAll by registering {
        allprojects {
            this@registering.dependsOn(tasks.withType<Detekt>())
        }
    }

    build {
        dependsOn(detektAll)
        finalizedBy(koverXmlReport, koverHtmlReport)
    }
}
