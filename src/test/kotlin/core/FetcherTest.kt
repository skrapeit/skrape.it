package core

import com.github.tomakehurst.wiremock.WireMockServer
import org.assertj.core.api.KotlinAssertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

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
        val fetched = Fetcher().fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(200)
        assertThat(fetched.response.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(fetched.response.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `can fetch url and use HTTP verb GET by default`() {
        // given
        wireMockServer.setupStub(path = "/example")

        // when
        val fetched = Fetcher(Params("https://localhost:8089/example")).fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(200)
        assertThat(fetched.response.contentType()).isEqualTo("text/html; charset=UTF-8")
        assertThat(fetched.response.parse().title()).isEqualTo("i'm the title")
    }

    @Test
    fun `will not throw exception on non existing url`() {
        // when
        val fetched = Fetcher(Params("http://localhost:8080/not-existing")).fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(404)
    }

    @Test
    fun `will not follow redirects if configured`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = Fetcher(Params(followRedirects = false)).fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(302)
    }

    @Test
    fun `will follow redirect by default`() {
        // given
        wireMockServer.setupRedirect()

        // when
        val fetched = Fetcher().fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(404)
    }

    @Test
    fun `can fetch url and use HTTP verb POST`() {
        // given
        wireMockServer.setupPostStub()

        // when
        val fetched = Fetcher(Params(
                method = HttpMethod.POST
        )).fetch()

        // then
        assertThat(fetched.response.statusCode()).isEqualTo(200)
        assertThat(fetched.response.contentType()).isEqualTo("application/json; charset=UTF-8")
        assertThat(fetched.response.body()).isEqualTo("""{"data":"some value"}""")
    }

    @Test
    fun `will throw exception on timeout`() {
        wireMockServer.setupStub(delay = 6000)

        assertThrows(SocketTimeoutException::class.java
        ) {
            Fetcher().fetch()
        }
    }

    @Test
    fun `response will include request params`() {
        // when
        val fetched = Fetcher().fetch()

        // then
        SoftAssertions.assertSoftly {
            it.apply {
                assertThat(fetched.requestParams.url).isEqualTo("http://localhost:8080")
                assertThat(fetched.requestParams.method).isEqualTo(HttpMethod.GET)
                assertThat(fetched.requestParams.userAgent).isEqualTo("Mozilla/5.0 skrape.it/0-SNAPSHOT")
                assertThat(fetched.requestParams.followRedirects).isTrue
                assertThat(fetched.requestParams.ignoreContentType).isTrue
                assertThat(fetched.requestParams.ignoreHttpErrors).isTrue
            }
        }
    }

    @Test
    fun `response will return configured request params`() {
        // when
        val fetched = Fetcher(Params(
                url = "https://localhost:8089",
                method = HttpMethod.POST
                )).fetch()

        // then
        SoftAssertions.assertSoftly {
            it.apply {
                assertThat(fetched.requestParams.url).isEqualTo("https://localhost:8089")
                assertThat(fetched.requestParams.method).isEqualTo(HttpMethod.POST)
                assertThat(fetched.requestParams.userAgent).isEqualTo("Mozilla/5.0 skrape.it/0-SNAPSHOT")
                assertThat(fetched.requestParams.followRedirects).isTrue
                assertThat(fetched.requestParams.ignoreContentType).isTrue
                assertThat(fetched.requestParams.ignoreHttpErrors).isTrue
            }
        }
    }
}
