import com.sun.net.httpserver.HttpServer
import it.skrape.core.htmlDocument
import it.skrape.fetcher.AsyncFetcher
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.InetSocketAddress

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BleedingEdgeReleaseTest {
    private lateinit var server: HttpServer
    private val url: String get() = "http://127.0.0.1:${server.address.port}/"

    @BeforeAll
    internal fun startServer() {
        val fixture = checkNotNull(javaClass.getResource("/project.html")).readBytes()
        server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0).apply {
            createContext("/") { exchange ->
                exchange.responseHeaders.add("Content-Type", "text/html; charset=UTF-8")
                exchange.sendResponseHeaders(200, fixture.size.toLong())
                exchange.responseBody.use { it.write(fixture) }
            }
            start()
        }
    }

    @AfterAll
    internal fun stopServer() {
        server.stop(0)
    }

    @Test
    internal fun `published pre-release can fetch and parse HTML`() {
        val stars = skrape(HttpFetcher) {
            request { url = this@BleedingEdgeReleaseTest.url }
            response {
                htmlDocument {
                    ".project-stars" { findFirst { text } }
                }
            }
        }
        expectThat(stars).isEqualTo("1234")
    }

    @Test
    internal fun `published pre-release can render JavaScript`() {
        val renderedText = skrape(BrowserFetcher) {
            request { url = this@BleedingEdgeReleaseTest.url }
            response {
                htmlDocument {
                    "#rendered" { findFirst { text } }
                }
            }
        }
        expectThat(renderedText).isEqualTo("Rendered by JavaScript")
    }

    @Test
    internal fun `published pre-release can fetch and parse HTML asynchronously`(): Unit = runBlocking {
        val stars = skrape(AsyncFetcher) {
            request { url = this@BleedingEdgeReleaseTest.url }
            response {
                htmlDocument {
                    ".project-stars" { findFirst { text } }
                }
            }
        }
        expectThat(stars).isEqualTo("1234")
    }
}
