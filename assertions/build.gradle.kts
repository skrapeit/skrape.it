plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.htmlParser)
    api(projects.dsl)
    api(projects.baseFetcher)
}
