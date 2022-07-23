package buildsrc.convention

import buildsrc.config.createSkrapeItPom
import buildsrc.config.credentialsAction
import buildsrc.config.isKotlinMultiplatformJavaEnabled
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

plugins {
  `maven-publish`
  signing
}

description = "Configuration for publishing to Sonatype Maven Central"

// in $GRADLE_USER_HOME/gradle.properties, set
// sonatypeRepositoryUsername=...
// sonatypeRepositoryPassword=...
// Or set environment variables
// ORG_GRADLE_PROJECT_sonatypeRepositoryUsername=...
// ORG_GRADLE_PROJECT_sonatypeRepositoryPassword=...
val sonatypeRepositoryCredentials: Provider<Action<PasswordCredentials>> =
  providers.credentialsAction("sonatypeRepository")

val sonatypeRepositoryReleaseUrl: Provider<String> = provider {
  if (version.toString().endsWith("SNAPSHOT")) {
    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
  } else {
    "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
  }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
  // Gradle warns about some signing tasks using publishing task outputs without explicit dependencies
  dependsOn(tasks.withType<Sign>())
  mustRunAfter(tasks.withType<Sign>())

  doLast {
    logger.lifecycle("[${this.name}] ${project.group}:${project.name}:${project.version}")
  }
}

afterEvaluate {
  // Register signatures afterEvaluate, otherwise the signing plugin creates the signing tasks
  // too early, before all the publications are added.

  if (sonatypeRepositoryCredentials.isPresent()) {
    // Use .all { }, not .configureEach { }, otherwise the signing plugin doesn't create the
    // signing tasks soon enough.
    publishing.publications.withType<MavenPublication>().all {
      signing.sign(this)
      logger.lifecycle("configuring signature for publication ${this.name}")
    }
  }
}

signing {
  if (sonatypeRepositoryCredentials.isPresent()) {

    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project

    if (
      signingKeyId != null &&
      signingKey != null &&
      signingPassword != null
    ) {
      useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    } else {
      useGpgCmd()
    }
  }
}

publishing {
  repositories {
    // publish to local dir, for testing
    maven(rootProject.layout.buildDirectory.dir("maven-internal")) {
      name = "LocalProjectDir"
    }
  }

  if (sonatypeRepositoryCredentials.isPresent()) {
    repositories {
      maven(sonatypeRepositoryReleaseUrl) {
        name = "sonatype"
        credentials(sonatypeRepositoryCredentials.get())
      }
    }
  }
  publications.withType<MavenPublication>().configureEach {
    createSkrapeItPom {
      name.set("skrape{it} ${project.name}")
    }
  }
}

plugins.withType<KotlinMultiplatformPluginWrapper>().configureEach {
  // if required, do specific Kotlin Multiplatform configuration
  // Kotlin Multiplatform automatically registers publications
  javadocStubTask()
}

plugins.withType<JavaPlugin>().configureEach {
  afterEvaluate {
    // Here we only want to apply config to non-KMP projects, so check to only do that if the
    // KMP plugin isn't also enabled (it automatically applies the Java plugin).
    if (!isKotlinMultiplatformJavaEnabled()) {
      publishing.publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
      }
    }
  }
}

/**
 * Sonatype requires a Javadoc jar, even if the project is not a Java project.
 *
 * [They recommend](https://central.sonatype.org/publish/requirements/#supply-javadoc-and-sources)
 * creating an empty Javadoc jar in this instance.
 */
fun Project.javadocStubTask(): Jar {
  logger.lifecycle("[${project.displayName}] stubbing Javadoc Jar")

  // use creating, not registering, because the signing plugin isn't compatible with
  // config-avoidance API
  val javadocJarStub by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Stub javadoc.jar artifact (required by Maven Central)"
    archiveClassifier.set("javadoc")
  }

  tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(javadocJarStub)
  }

  publishing {
    publications.withType<MavenPublication>().configureEach {
      artifact(javadocJarStub)
    }
  }

  if (sonatypeRepositoryCredentials.isPresent()) {
    val signingTasks = signing.sign(javadocJarStub)
    tasks.withType<AbstractPublishToMaven>().configureEach {
      signingTasks.forEach { signingTask ->
        dependsOn(signingTask)
      }
    }
  }

  return javadocJarStub
}
