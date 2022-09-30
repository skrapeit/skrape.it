object Versions {
    const val kotlin = "1.7.10"
    const val coroutines = "1.6.3"
    const val ktor = "2.0.3"
    const val serialization = "1.0.1"
    const val datetime = "0.1.1"
    const val jsoup = "1.13.1" // 1.14.3
    const val htmlUnit = "2.63.0"
    const val testContainers = "1.16.2"
    const val wireMock = "2.28.0"
    const val log4jOverSlf4j = "1.7.36"
    const val logback = "1.2.7"
    const val strikt = "0.33.0"
    const val mockk = "1.12.1"
    const val jUnit = "5.8.2"
    const val restAssured = "4.4.0"
    const val javaxServlet = "4.0.1"
    const val spring = "5.3.13"
    const val jetbrainsAnnotations = "23.0.0"
    const val atrium = "0.18.0"
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
    const val htmlUnit = "net.sourceforge.htmlunit:htmlunit-android:${Versions.htmlUnit}"
    const val wireMock = "com.github.tomakehurst:wiremock-jre8:${Versions.wireMock}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val log4jOverSlf4j = "org.slf4j:log4j-over-slf4j:${Versions.log4jOverSlf4j}"
    const val strikt = "io.strikt:strikt-core:${Versions.strikt}"
    const val jUnit = "org.junit.jupiter:junit-jupiter:${Versions.jUnit}"
    const val javaxServlet = "javax.servlet:javax.servlet-api:${Versions.javaxServlet}"
    const val jetbrainsAnnotations = "org.jetbrains:annotations:${Versions.jetbrainsAnnotations}"
    const val restAssured = "io.rest-assured:kotlin-extensions:${Versions.restAssured}"

    object Kotlin : DependencyGroup(
        group = "org.jetbrains.kotlin",
        version = Versions.kotlin
    ) {
        val reflect = dependency("kotlin-reflect")
        val test = dependency("test")
    }

    object Ktor : DependencyGroup(
        group = "io.ktor",
        version = Versions.ktor
    ) {
        val client = dependency("ktor-client-core")
        val clientJS = dependency("ktor-client-js")
        val clientJson = dependency("ktor-client-json")
        val clientApache = dependency("ktor-client-apache")
        val clientLogging = dependency("ktor-client-logging")
        val serverNetty = dependency("ktor-server-netty")
        val serverTestHost = dependency("ktor-server-test-host")
        val freemarker = dependency("ktor-server-freemarker")
        val locations = dependency("ktor-server-locations")

        val server = dependency("ktor-server-core")
    }

    object Atrium : DependencyGroup(
        group = "ch.tutteli.atrium",
        version = Versions.atrium
    ) {
        val fluentCommon = dependency("atrium-fluent-en_GB-common")
        val fluentJvm = dependency("atrium-fluent-en_GB")
        val fluentJs = dependency("atrium-fluent-en_GB-js")
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

    object Spring : DependencyGroup(
        group = "org.springframework",
        version = Versions.spring
    ) {
        val webMvc = dependency("spring-webmvc")
        val test = dependency("spring-test")
    }
}
