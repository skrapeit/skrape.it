package it.skrape.fetcher

import Testcontainer
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
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

    @Suppress("UNCHECKED_CAST")
    class KtorBlockingFetcher(val ktorClient: HttpClient) : BlockingFetcher<HttpRequestBuilder> {
        override fun fetch(request: HttpRequestBuilder): Result = runBlocking {
            with(ktorClient.request(request)) {
                Result(
                    responseBody = bodyAsText(),
                    responseStatus = Result.Status(status.value, status.description),
                    contentType = contentType()?.toString(),
                    headers = headers.toMap().mapValues { it.value.firstOrNull().orEmpty() },
                    baseUri = request.url.toString(),
                    cookies = listOf(setCookie()) as List<Cookie>,
                )
            }
        }

        override val requestBuilder: HttpRequestBuilder
            get() = HttpRequestBuilder()
    }

    private val ktorClient = HttpClient(Apache)

    @Test
    fun `dsl can skrape by url`() {
        wiremock.setupStub(path = "/example")

        val result = skrape(KtorBlockingFetcher(ktorClient)) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response { this }
        }

        expectThat(result.responseStatus.code).isEqualTo(200)
        expectThat(result.responseBody).contains("i'm the title")
    }
}
