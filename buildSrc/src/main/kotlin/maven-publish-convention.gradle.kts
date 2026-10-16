plugins {
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

val rootProjectName = rootProject.name
val sourceRevision = providers.environmentVariable("GITHUB_SHA").orElse("master")

mavenPublishing {
    val artifactId = if (project.name == rootProjectName) project.name else "${rootProjectName}-${project.name}"

    coordinates(artifactId = artifactId)
    pom {
        name.set("skrape{it} ${project.name}")
    }
}

dokka {
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(rootProject.layout.projectDirectory)
            remoteUrl(sourceRevision.map { "https://github.com/skrapeit/skrape.it/blob/$it" })
            remoteLineSuffix.set("#L")
        }
    }
}
