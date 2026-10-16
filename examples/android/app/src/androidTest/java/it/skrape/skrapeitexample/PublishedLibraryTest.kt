package it.skrape.skrapeitexample

import androidx.test.ext.junit.runners.AndroidJUnit4
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PublishedLibraryTest {
    @Test
    fun parsesHtmlFromPublishedArtifact() {
        assertEquals("Local fixture", LibrarySmokeProbe.parse(HTML))
    }

    @Test
    fun fetchesAndParsesHttpResponse() {
        assertFetchesLocalFixture(LibrarySmokeProbe::fetch)
    }

    @Test
    fun fetchesAndParsesHttpResponseAsynchronously() {
        assertFetchesLocalFixture(LibrarySmokeProbe::fetchAsync)
    }

    @Test
    fun rendersJavaScriptWithPublishedBrowserFetcher() {
        val html = """
            <html><body><p id="rendered">Before</p>
            <script>document.getElementById('rendered').textContent = 'Before'.replace(/Before/, 'After');</script>
            </body></html>
        """.trimIndent()
        assertEquals("After", LibrarySmokeProbe.renderJavaScript(html))
    }

    private companion object {
        const val HTML = "<html><head><title>Local fixture</title></head><body>Example</body></html>"

        fun assertFetchesLocalFixture(fetch: (String) -> String) {
            MockWebServer().use { server ->
                server.enqueue(MockResponse.Builder().body(HTML).build())
                server.start()
                assertEquals("Local fixture", fetch(server.url("/").toString()))
            }
        }
    }
}
