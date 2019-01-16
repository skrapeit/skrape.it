package core

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FetcherTest {

    private val wireMockServer = WireMockServer(8080, 8089)

    @BeforeEach
    fun setup() {
        wireMockServer.start()
    }

    @AfterEach
    fun teardown() {
        wireMockServer.stop()
    }

    @Test
    fun `will fetch localhost 8080 with defaults if no params`() {
        // given
        setupStub()

        // when
        val response = Fetcher().fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(response.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(response.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can fetch url and use HTTP verb GET by default`() {
        // given
        setupStub(path = "/example")

        // when
        val response = Fetcher(Params("https://localhost:8089/example")).fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(response.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(response.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        // given
        setupPostStub()

        // when
        val response = Fetcher(Params(
                url = "http://localhost:8080",
                method = HttpMethod.POST
        )).fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(response.contentType()).isEqualTo("application/json; charset=UTF-8")
        assertThat(response.body()).isEqualTo("""{"data":"some value"}""")
    }

    private fun setupStub(
            path: String = "/",
            statusCode: Int = 200,
            contentType: String = "text/html; charset=UTF-8",
            fileName: String = "example.html"
    ) {
        wireMockServer.stubFor(get(urlEqualTo(path))
                .willReturn(aResponse().withHeader("Content-Type", contentType)
                        .withStatus(statusCode)
                        .withBodyFile(fileName)))
    }

    private fun setupPostStub() {
        wireMockServer.stubFor(post(urlEqualTo("/"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json; charset=UTF-8")
                        .withStatus(200)
                        .withBodyFile("data.json")))
    }
}
