package buildsrc.config

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

fun MavenPublication.createSkrapeItPom(
    configure: MavenPom.() -> Unit = {}
): Unit = pom {
    name.set("skrape{it}")
    description.set("A Kotlin-based testing/scraping/parsing library providing the ability to analyze and extract data from HTML (server & client-side rendered). It places particular emphasis on ease of use and a high level of readability by providing an intuitive DSL. First and foremost it aims to be a testing lib, but it can also be used to scrape websites in a convenient fashion.")
    url.set("https://docs.skrape.it")
    licenses {
        license {
            name.set("MIT License")
            url.set("https://opensource.org/licenses/MIT")
        }
    }
    developers {
        developer {
            id.set("christian-draeger")
            name.set("Christian Dräger")
        }
    }
    scm {
        connection.set("scm:git:git://github.com/skrapeit/skrape.it.git")
        developerConnection.set("scm:git:ssh://github.com:skrapeit/skrape.it.git")
        url.set("https://github.com/skrapeit/skrape.it/tree/master")
    }
    configure()
}

/**
 * Fetches credentials from `gradle.properties`, environment variables, or command line args.
 *
 * See https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:handling_credentials
 */
// https://github.com/gradle/gradle/issues/20925
fun ProviderFactory.credentialsAction(
    repositoryName: String
): Provider<Action<PasswordCredentials>> = zip(
    gradleProperty("${repositoryName}Username"),
    gradleProperty("${repositoryName}Password"),
) { user, pass ->
    Action<PasswordCredentials> {
        username = user
        password = pass
    }
}

/**
 * Check if a Kotlin Mutliplatform project also has Java enabled.
 *
 * Logic from [KotlinJvmTarget.withJava]
 */
fun Project.isKotlinMultiplatformJavaEnabled(): Boolean {
    val multiplatformExtension: KotlinMultiplatformExtension? =
        extensions.findByType(KotlinMultiplatformExtension::class)

    return multiplatformExtension?.targets
        ?.any { target -> target is KotlinJvmTarget && target.withJavaEnabled }
        ?: false
}
