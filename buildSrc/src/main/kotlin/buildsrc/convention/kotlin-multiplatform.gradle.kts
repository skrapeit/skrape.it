package buildsrc.convention

plugins {
    kotlin("multiplatform") apply false
    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.detekt")
}

configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Deps.Atrium.fluentCommon)
            }
        }
    }
}

afterEvaluate {
    configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
        sourceSets {
            val commonTest by getting
            //Find all test sourceSets (every one should depend on commonTest) to enable ExperimentalCoroutinesApi
            val testSourceSets = matching {
                if (it == commonTest) return@matching true
                if (it.dependsOn.isEmpty()) return@matching false
                val toCheck = it.dependsOn.toMutableList()
                while (toCheck.isNotEmpty()) {
                    val check = toCheck.removeAt(0)
                    if (check == commonTest) return@matching true
                    toCheck += check.dependsOn
                }
                return@matching false
            }
            testSourceSets.onEach { println("Test sourceSet $it") }.forEach { it.languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi") }
        }
    }
}
