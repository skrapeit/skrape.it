@file:Suppress("PropertyName")

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    val htmlUnitVersion = "2.48.0"
    implementation(project(":fetcher:base-fetcher"))
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion") {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    val log4jOverSlf4jVersion = "1.7.30"
    val logbackVersion = "1.2.3"
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.slf4j:log4j-over-slf4j:$log4jOverSlf4jVersion")

    testImplementation(project(path = ":test-utils", configuration = "default"))
}
