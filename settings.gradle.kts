import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("1.0.0")
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.1"
}

rootProject.name = "skrapeit"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/") {
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}

kover {
    enableCoverage()
    reports {
        verify {
            rule {
                bound {
                    minValue = 80
                    coverageUnits = CoverageUnit.LINE
                }
            }
        }
    }
}

include(":assertions")
include(":dsl")
include(":examples:scraping")
include(":examples:use-pre-release-version")
include(":fetcher:base-fetcher")
include(":fetcher:http-fetcher")
include(":fetcher:async-fetcher")
include(":fetcher:browser-fetcher")
include(":html-parser")
include(":integrationtests")
include(":ktor-extension")
include(":mock-mvc-extension")
include(":test-utils")
