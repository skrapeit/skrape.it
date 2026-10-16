package it.skrape.fetcher

import com.sun.net.httpserver.HttpServer
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.InetSocketAddress

class HttpFetcherContractTest {
    @TestFactory
    fun `both fetchers preserve streamed responses`(): List<DynamicTest> {
        val fetchers: Map<String, (Request) -> Result> = mapOf(
            "HTTP" to HttpFetcher::fetch,
            "Async" to { request -> runBlocking { AsyncFetcher.fetch(request) } },
        )
        return fetchers.flatMap { (name, fetch) ->
            listOf(200, 503).map { status ->
                dynamicTest("$name reads a complete UTF-8 response with status $status") {
                    val server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)
                    val chunk = "<p>Grüße aus dem Stream</p>".repeat(1024)
                    server.createContext("/") { exchange ->
                        exchange.responseHeaders.add("Content-Type", "text/html; charset=UTF-8")
                        exchange.responseHeaders.add("X-Trace", "first")
                        exchange.responseHeaders.add("X-Trace", "second")
                        exchange.responseHeaders.add("Set-Cookie", "session=example; SameSite=Strict")
                        exchange.sendResponseHeaders(status, 0)
                        exchange.responseBody.bufferedWriter(Charsets.UTF_8).use { writer ->
                            repeat(32) {
                                writer.write(chunk)
                                writer.flush()
                            }
                        }
                    }
                    server.start()
                    try {
                        val result = fetch(Request(url = "http://127.0.0.1:${server.address.port}/"))
                        expectThat(result.responseStatus.code).isEqualTo(status)
                        expectThat(result.responseBody).isEqualTo(chunk.repeat(32))
                        expectThat(result.contentType).isEqualTo("text/html;charset=UTF-8")
                        expectThat(result.headers.entries.single { it.key.equals("X-Trace", true) }.value)
                            .isEqualTo("first")
                        expectThat(result.cookies.single().sameSite).isEqualTo(SameSite.STRICT)
                    } finally {
                        server.stop(0)
                    }
                }
            }
        }
    }
}
