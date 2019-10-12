import org.gradle.api.tasks.bundling.Jar

plugins {
    kotlin("jvm") version "1.3.50"
    `maven-publish`
    jacoco
    id("com.github.ben-manes.versions") version "0.26.0"
    id("com.adarshr.test-logger") version "1.7.0"
}

testlogger {
    setTheme("mocha")
    slowThreshold = 1000
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.11.3")
    implementation("com.willowtreeapps.assertk:assertk-jvm:0.13")
    implementation("net.sourceforge.htmlunit:htmlunit:2.36.0")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.50")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("io.mockk:mockk:1.9.3.kotlin12")
    testImplementation("io.mockk:mockk-dsl-jvm:1.9.3.kotlin12")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.25.0")
    testImplementation("org.testcontainers:testcontainers:1.11.2")
    testImplementation("org.testcontainers:junit-jupiter:1.11.2")
    testImplementation("ch.qos.logback:logback-classic:1.2.3")
    testImplementation("org.slf4j:log4j-over-slf4j:1.7.26")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    artifacts {
        archives(sourcesJar)
        archives(jar)
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

group = "it.skrape"
version = "0.6.0"
description = "skrape{it}"

publishing {
    publications {
        register<MavenPublication>("default") {
            artifactId = "skrapeit-core"
            from(components["java"])
            artifact(tasks["sourcesJar"])
            pom {
                name.set("skrape{it}")
                description.set("""skrape{it} is a Kotlin-based HTML testing and web scraping library 
                    that can be used seamlessly in both Spring-Boot and other JVM projects. 
                    It places particular emphasis on ease of use, a high level of readability 
                    and attention to performance through the use of non-blocking operations.""")
                url.set("https://docs.skrape.it")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("christian-draeger")
                        name.set("Christian Dräger")
                        email.set("christian.draeger1@gmail.com")
                        url.set("https://github.com/christian-draeger")
                        timezone.set("Europe/Berlin")
                        properties.put("twitter", "@JvmDefault")
                    }
                }
                issueManagement {
                    system.set("github")
                    url.set("https://github.com/skrapeit/skrape.it/issues")
                }
                scm {
                    connection.set("scm:git:git://github.com/skrapeit/skrape.it.git")
                    developerConnection.set("scm:git:ssh://github.com:skrapeit/skrape.it.git")
                    url.set("https://github.com/skrapeit/skrape.it/tree/master")
                }
                packaging = "jar"
            }
        }
    }
    repositories {
        maven {
            // This will publish the artifacts to a local repository in the build dir
            url = uri("$buildDir/repository")
        }
    }
}