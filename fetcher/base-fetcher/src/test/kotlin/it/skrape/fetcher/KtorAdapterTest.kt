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
import setupStub
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo

private val wiremock = Testcontainer.wiremock

class KtorAdapterTest {


    class KtorBlockingFetcher(val ktorClient: HttpClient) : Fetcher<HttpRequestBuilder> {
        override fun fetch(request: HttpRequestBuilder): Result {
            return runBlocking {
                val fullResponse = ktorClient.request<HttpResponse>(request)

                Result(
                    responseBody = fullResponse.readText(),
                    responseStatus = Result.Status(fullResponse.status.value, fullResponse.status.description),
                    contentType = fullResponse.contentType()?.toString(),
                    headers = fullResponse.headers.toMap().mapValues { it.value.firstOrNull().orEmpty() },
                    baseUri = fullResponse.request.url.toString(),
                    cookies = listOf(fullResponse.setCookie()) as List<Cookie>
                )
            }
        }

        override val requestBuilder: HttpRequestBuilder
            get() = HttpRequestBuilder()
    }

    val KTOR_CLIENT = HttpClient(Apache)

    @Test
    fun `dsl can skrape by url`() {
        wiremock.setupStub(path = "/example")

        val result = skrape(KtorBlockingFetcher(KTOR_CLIENT)) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            extract { this }
        }

        expectThat(result.responseStatus.code).isEqualTo(200)
        expectThat(result.responseBody).contains("i'm the title")
    }
}