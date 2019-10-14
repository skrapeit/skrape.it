plugins {
    kotlin("jvm") version "1.3.50"
    `maven-publish`
    jacoco
    id("com.github.ben-manes.versions") version "0.26.0"
    id("com.adarshr.test-logger") version "2.0.0"
}

testlogger {
    setTheme("mocha")
    slowThreshold = 1000
}

repositories {
    jcenter()
}

dependencies {
    val kotlinVersion = "1.3.50"
    val jsoupVersion = "1.11.3"
    val htmlUnitVersion = "2.36.0"
    val assertkVersion = "0.13"
    val striktVersion = "0.22.2"
    val kohttpVersion = "0.11.0"

    val junitVersion = "5.5.2"
    val testContainersVersion = "1.12.2"
    val wireMockVersion = "2.25.0"
    val mockkVersion = "1.9.3"
    val log4jOverSlf4jVersion = "1.7.28"
    val logbackVersion = "1.2.3"

    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("io.strikt:strikt-core:$striktVersion")
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion")
    implementation("io.github.rybalkinsd:kohttp:$kohttpVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.mockk:mockk-dsl-jvm:$mockkVersion")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wireMockVersion")
    testImplementation("org.testcontainers:testcontainers:$testContainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")
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