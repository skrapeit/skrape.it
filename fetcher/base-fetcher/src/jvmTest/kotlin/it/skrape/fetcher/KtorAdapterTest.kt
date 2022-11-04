package it.skrape.fetcher

import Testcontainer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import setupStub
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo

private val wiremock = Testcontainer.wiremock

@DisabledOnOs(OS.WINDOWS)
class KtorAdapterTest {

    @Test
    fun `dsl can skrape by url`() = runTest {
        wiremock.setupStub(path = "/example")

        val result = skrape {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response { this }
        }

        expectThat(result.responseStatus.code).isEqualTo(200)
        expectThat(result.responseBody).contains("i'm the title")
    }
}
