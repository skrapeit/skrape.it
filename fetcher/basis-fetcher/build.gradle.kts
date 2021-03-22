@file:Suppress("PropertyName")

val kotlin_version: String by project

plugins {
    jacoco
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(project(":dsl"))

    val htmlUnitVersion = "2.44.0"
    val ktorVersion = "1.4.1"
    implementation("net.sourceforge.htmlunit:htmlunit:$htmlUnitVersion") {
        exclude("org.eclipse.jetty.websocket") // avoid android crash; see #93
    }
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version") // needed for extractIt when creating instance

    testImplementation(project(path = ":test-utils", configuration = "default"))
    testImplementation("io.ktor:ktor-client-core:$ktorVersion")
    testImplementation("io.ktor:ktor-client-apache:$ktorVersion")

}
