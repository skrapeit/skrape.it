abstract class DependencyGroup(
    val group: String,
    val version: String
) {
    fun dependency(
        name: String,
        group: String = this.group,
        version: String = this.version
    ) = "$group:$name:$version"
}

object Versions {
    const val kotlin = "1.4.31"
    const val coroutines = "1.4.3"
    const val ktor = "1.5.2"
    const val serialization = "1.0.1"
    const val datetime = "0.1.1"
    const val jsoup = "1.13.1"
    const val htmlUnit = "2.47.1"
    const val kohttp = "0.12.0"
    const val testContainers = "1.15.2"
    const val wireMock = "2.27.2"
    const val log4jOverSlf4j = "1.7.30"
    const val logback = "1.2.3"
    const val okHttp = "4.9.1"
}

object Deps {

    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val htmlUnit = "net.sourceforge.htmlunit:htmlunit:${Versions.htmlUnit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val kOHttp = "io.github.rybalkinsd:kohttp:${Versions.kohttp}"
    const val wireMock = "com.github.tomakehurst:wiremock-jre8:${Versions.wireMock}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val log4jOverSlf4j = "org.slf4j:log4j-over-slf4j:${Versions.log4jOverSlf4j}"

    object Ktor : DependencyGroup(
        group = "io.ktor",
        version = Versions.ktor
    ) {
        val client = dependency("ktor-client-core")
        val clientJson = dependency("ktor-client-json")
        val clientApache = dependency("ktor-client-apache")
    }

    object KotlinX {
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

        object Coroutines : DependencyGroup(
            group = "org.jetbrains.kotlinx",
            version = Versions.coroutines
        ) {
            val test = dependency("kotlinx-coroutines-test")
            val core = dependency("kotlinx-coroutines-core")
        }
    }

    object TestContainers : DependencyGroup(
        group = "org.testcontainers",
        version = Versions.testContainers
    ) {
        val testContainers = dependency("testcontainers")
        val jUnit = dependency("junit-jupiter")
    }
}




