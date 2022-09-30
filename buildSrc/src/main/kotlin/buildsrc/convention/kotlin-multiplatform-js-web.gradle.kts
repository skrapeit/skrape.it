package buildsrc.convention

plugins {
    id("buildsrc.convention.kotlin-multiplatform")
    kotlin("multiplatform")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    js {
        browser {
            //Setup webpack to use a common directory for it's configuration
            //TODO: Maybe write a task to enable per project configs additionally to a general webpack config
            commonWebpackConfig {
                configDirectory = rootProject.projectDir.toPath().resolve("webpack.config.d").toFile()
            }
            //Setup test task to use karma.
            // A common karma config directory is used and the project location is supplied via an environment-variable
            testTask {
                environment("PROJECT_PATH", projectDir.absolutePath)
                useKarma {
                    useFirefoxHeadless()
                    useChromeHeadless()
                    useSourceMapSupport()
                    useConfigDirectory(rootProject.projectDir.toPath().resolve("karma.config.d").toFile())
                }
            }
        }
        useCommonJs()
    }

    sourceSets {
        val jsTest by getting {
            dependencies {
                implementation(Deps.Atrium.fluentJs)
            }
        }
    }
}