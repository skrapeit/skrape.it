package it.skrape.core.fetcher

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import io.ktor.http.contentType
import io.ktor.util.toMap
import it.skrape.WireMockSetup
import it.skrape.core.htmlDocument
import it.skrape.expect
import it.skrape.matchers.ContentTypes
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentTimes
import it.skrape.selects.html5.p
import it.skrape.selects.html5.title
import it.skrape.setupStub
import it.skrape.skrape
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class KtorAdapterTest : WireMockSetup() {
    class KtorBlockingFetcher(val ktorClient: HttpClient) : Fetcher<HttpRequestBuilder> {
        override fun fetch(request: HttpRequestBuilder): Result {
            return runBlocking {
                val fullResponse = ktorClient.request<HttpResponse>(request)

                Result(
                        fullResponse.readText(),
                        Result.Status(fullResponse.status.value, fullResponse.status.description),
                        fullResponse.contentType()?.toString(),
                        fullResponse.headers.toMap().mapValues { it.value.firstOrNull().orEmpty() },
                        fullResponse.request.url.toString()
                )
            }
        }

        override val requestBuilder: HttpRequestBuilder
            get() = HttpRequestBuilder()
    }

    val KTOR_CLIENT = HttpClient(Apache)

    @Test
    internal fun `dsl can skrape by url`() {
        wireMockServer.setupStub(path = "/example")

        skrape(KtorBlockingFetcher(KTOR_CLIENT)) {
            request {
                url("http://localhost:8080/example")
            }

            expect {

                status {
                    code toBe 200
                    message toBe "OK"
                }

                contentType toBe ContentTypes.TEXT_HTML_UTF8

                htmlDocument {

                    title {
                        findFirst {
                            text toBe "i'm the title"
                        }
                    }

                    p {
                        findAll {
                            toBePresentTimes(2)
                        }
                        findFirst {
                            attribute("data-foo") toBe "bar"
                            text toBe "i'm a paragraph"
                        }

                        findLast {
                            text toBe "i'm a second paragraph"
                        }
                    }
                }
            }
        }
    }
}