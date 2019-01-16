package core

import com.github.tomakehurst.wiremock.WireMockServer
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
        wireMockServer.setupStub()

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
        wireMockServer.setupStub(path = "/example")

        // when
        val response = Fetcher(Params("https://localhost:8089/example")).fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(response.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(response.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `will not throw exception on non existing url`() {
        // when
        val response = Fetcher(Params("http://localhost:8080/not-existing")).fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val response = Fetcher(Params(followRedirects = false)).fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(302)
    }

    @Test
    fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val response = Fetcher().fetch()

        // then
        assertThat(response.statusCode()).isEqualTo(404)
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()

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
}
