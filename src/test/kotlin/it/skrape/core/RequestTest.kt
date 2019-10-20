package it.skrape.core

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class RequestTest {

    @Test
    internal fun `can build valid url by default`() {
        val url = Request().url
        expectThat(url).isEqualTo("http://localhost:8080/")
    }

    @Test
    internal fun `can build valid url with defined protocol`() {
        val url = Request(protocol = Protocol.HTTPS).url
        expectThat(url).isEqualTo("https://localhost:8080/")
    }

    @Test
    internal fun `can build valid url with defined hostname`() {
        val url = Request(host = "skrape.it").url
        expectThat(url).isEqualTo("http://skrape.it:8080/")
    }

    @Test
    internal fun `can build valid url with defined port`() {
        val url = Request(port = 8181).url
        expectThat(url).isEqualTo("http://localhost:8181/")
    }

    @Test
    internal fun `can build valid url with defined path`() {
        val url = Request(path = "/foo").url
        expectThat(url).isEqualTo("http://localhost:8080/foo")
    }
}