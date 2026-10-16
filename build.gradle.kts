import dev.detekt.gradle.Detekt

plugins {
    id("version-management-convention")
    id("kotlin-library-convention")
}

val bundledModules = listOf(
    projects.assertions,
    projects.fetcher.asyncFetcher,
    projects.fetcher.baseFetcher,
    projects.fetcher.browserFetcher,
    projects.fetcher.httpFetcher,
    projects.dsl,
    projects.htmlParser,
)

dependencies {
    bundledModules.forEach {
        api(it)
        dokka(it)
    }
    dokka(projects.ktorExtension)
    dokka(projects.mockMvcExtension)
}

val detektAll = tasks.register("detektAll") {
    allprojects {
        dependsOn(tasks.withType<Detekt>())
    }
}

tasks {
    build {
        dependsOn(detektAll)
        finalizedBy(koverXmlReport, koverHtmlReport)
    }
}
