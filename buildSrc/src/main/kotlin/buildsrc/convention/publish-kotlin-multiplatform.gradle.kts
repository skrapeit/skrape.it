package buildsrc.convention

import buildsrc.config.createSkrapeItPom
import gradle.kotlin.dsl.accessors._4ad077ad74816558e52d7069eb18a2f7.publishing
import gradle.kotlin.dsl.accessors._4ad077ad74816558e52d7069eb18a2f7.signing
import org.gradle.api.provider.Provider
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.provideDelegate

plugins {
    `maven-publish`
    signing
}

description = "Configuration for publishing Kotlin Multiplatform libraries to Sonatype Maven Central"

val signingKeyId: String? by project
val signingKey: String? by project
val signingPassword: String? by project

val signingEnabled: Provider<Boolean> = provider {
    signingKeyId != null && signingKey != null && signingPassword != null
}

val javadocJar by tasks.registering(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Empty Javadoc Jar (required by Maven Central)"
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        // publish to local dir, for testing
        maven(rootProject.layout.buildDirectory.dir("maven-internal")) {
            name = "LocalProjectDir"
        }
    }
    publications.withType<MavenPublication>().configureEach {
        artifact(javadocJar)

        createSkrapeItPom {
            name.set("skrape{it} ${project.name}")
        }
    }
}

signing {
    if (signingEnabled.get()) {
        sign(publishing.publications)

        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    }
}
