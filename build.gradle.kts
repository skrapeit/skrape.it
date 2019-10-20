import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    kotlin("jvm") version "1.3.50"
    jacoco
    id("org.jetbrains.dokka") version "0.10.0"
    id("com.github.ben-manes.versions") version "0.26.0"
    id("com.adarshr.test-logger") version "2.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.1.1"
    id("com.vanniktech.maven.publish") version "0.8.0"
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
    val jsoupVersion = "1.12.1"
    val htmlUnitVersion = "2.36.0"
    val assertkVersion = "0.17"
    val striktVersion = "0.22.2"
    val kohttpVersion = "0.11.0"

    val junitVersion = "5.5.2"
    val testContainersVersion = "1.12.2"
    val wireMockVersion = "2.25.1"
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

detekt {
    toolVersion = "1.1.1"
    input = files(DetektExtension.DEFAULT_SRC_DIR_KOTLIN)
    config = files("$projectDir/src/test/resources/detekt.yml")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
        }
    }

    test {
        dependsOn(detekt)
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        finalizedBy(jacocoTestReport)
    }
}

mavenPublish {
    useMavenPublish = true
}
