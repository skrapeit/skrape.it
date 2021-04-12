object Versions {
    const val kotlin = "1.4.32"
    const val coroutines = "1.4.3"
    const val ktor = "1.5.3"
    const val serialization = "1.0.1"
    const val datetime = "0.1.1"
    const val jsoup = "1.13.1"
    const val htmlUnit = "2.49.1"
    const val kohttp = "0.12.0"
    const val testContainers = "1.15.2"
    const val wireMock = "2.27.2"
    const val log4jOverSlf4j = "1.7.30"
    const val logback = "1.2.3"
    const val okHttp = "4.9.1"
    const val strikt = "0.30.1"
    const val mockk = "1.11.0"
    const val jUnit = "5.7.1"
    const val restAssured = "4.3.3"
    const val javaxServlet = "4.0.1"
    const val spring = "5.3.5"
    const val jetbrainsAnnotations = "20.1.0"
}

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

object Deps {

    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val htmlUnit = "net.sourceforge.htmlunit:htmlunit:${Versions.htmlUnit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val koHttp = "io.github.rybalkinsd:kohttp:${Versions.kohttp}"
    const val wireMock = "com.github.tomakehurst:wiremock-jre8:${Versions.wireMock}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val log4jOverSlf4j = "org.slf4j:log4j-over-slf4j:${Versions.log4jOverSlf4j}"
    const val strikt = "io.strikt:strikt-core:${Versions.strikt}"
    const val jUnit = "org.junit.jupiter:junit-jupiter:${Versions.jUnit}"
    const val javaxServlet = "javax.servlet:javax.servlet-api:${Versions.javaxServlet}"
    const val jetbrainsAnnotations = "org.jetbrains:annotations:${Versions.jetbrainsAnnotations}"

    object Kotlin : DependencyGroup(
        group = "org.jetbrains.kotlin",
        version = Versions.kotlin
    ) {
        val reflect = dependency("kotlin-reflect")
    }

    object Ktor : DependencyGroup(
        group = "io.ktor",
        version = Versions.ktor
    ) {
        val client = dependency("ktor-client-core")
        val clientJson = dependency("ktor-client-json")
        val clientApache = dependency("ktor-client-apache")
        val clientLogging = dependency("ktor-client-logging")
        val serverNetty = dependency("ktor-server-netty")
        val serverTestHost = dependency("ktor-server-test-host")
        val freemarker = dependency("ktor-freemarker")
        val locations = dependency("ktor-locations")
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
            val jdk8 = dependency("kotlinx-coroutines-jdk8")
        }
    }

    object TestContainers : DependencyGroup(
        group = "org.testcontainers",
        version = Versions.testContainers
    ) {
        val testContainers = dependency("testcontainers")
        val jUnit = dependency("junit-jupiter")
    }

    object Mockk : DependencyGroup(
        group = "io.mockk",
        version = Versions.mockk
    ) {
        val mockk = dependency("mockk")
        val dslJvm = dependency("mockk-dsl-jvm")
    }

    object RestAssured : DependencyGroup(
        group = "io.rest-assured",
        version = Versions.restAssured
    ) {
        val restAssured = dependency("rest-assured")
        val restAssuredAll = dependency("rest-assured-all")
    }

    object Spring : DependencyGroup(
        group = "org.springframework",
        version = Versions.spring
    ) {
        val webMvc = dependency("spring-webmvc")
        val test = dependency("spring-test")
    }
}
