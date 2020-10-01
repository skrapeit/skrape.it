package it.skrape.core.fetcher

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import it.skrape.WireMockSetup
import it.skrape.core.htmlDocument
import it.skrape.expect
import it.skrape.matchers.ContentTypes
import it.skrape.matchers.toBe
import it.skrape.matchers.toBeEmpty
import it.skrape.matchers.toBePresentTimes
import it.skrape.selects.html5.p
import it.skrape.selects.html5.title
import it.skrape.setupStub
import it.skrape.skrape
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class KtorAdapterTest : WireMockSetup() {
    class KtorBlockingFetcher(val ktorClient: HttpClient) : Fetcher<HttpRequestBuilder> {
        override fun fetch(request: HttpRequestBuilder): Result {
            return runBlocking {
                val fullResponse = ktorClient.request<HttpResponse>(request)

                Result(
                        fullResponse.readText(),
                        Result.Status(fullResponse.status.value, fullResponse.status.description),
                        fullResponse.contentType()?.toString(),
                        fullResponse.headers.toMap().mapValues { it.value.firstOrNull().orEmpty() },
                        fullResponse.request.url.toString(),
                        fullResponse.setCookie() as List<Cookie>
                )
            }
        }

        override val requestBuilder: HttpRequestBuilder
            get() = HttpRequestBuilder()
    }

    val KTOR_CLIENT = HttpClient(Apache)

    @Test
    fun `dsl can skrape by url`() {
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

                cookies {
                    toBeEmpty
                }

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