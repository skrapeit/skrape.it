package it.skrape.fetcher

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class UrlBuilderTest {

    @Test
    fun `can build valid url by default`() {
        val url = UrlBuilder().toString()
        expectThat(url).isEqualTo("http://localhost:8080/")
    }

    @ParameterizedTest(name = "can build valid {0}-Url if protocol param is defined")
    @EnumSource(UrlBuilder.Protocol::class)
    fun `can build valid url with defined protocol`(protocol: UrlBuilder.Protocol) {
        val url = UrlBuilder(protocol = protocol).toString()
        expectThat(url).isEqualTo("${protocol.value}localhost:8080/")
    }

    @Test
    fun `can build valid url with defined hostname`() {
        val url = UrlBuilder(host = "skrape.it").toString()
        expectThat(url).isEqualTo("http://skrape.it:8080/")
    }

    @Test
    fun `can build valid url with defined port`() {
        val url = UrlBuilder(port = 8181).toString()
        expectThat(url).isEqualTo("http://localhost:8181/")
    }

    @Test
    fun `can build valid url with defined path`() {
        val url = UrlBuilder(path = "/foo").toString()
        expectThat(url).isEqualTo("http://localhost:8080/foo")
    }

    @Test
    fun `can build valid url by the use of several options`() {
        val url = UrlBuilder(
                protocol = UrlBuilder.Protocol.HTTPS,
                path = "/my-path",
                host = "skrape.it",
                port = 12345,
                queryParam = mapOf("skrape" to "it")
        ).toString()
        expectThat(url).isEqualTo("https://skrape.it:12345/my-path?skrape=it")
    }

    @Test
    fun `can build valid url with ONE query parameter`() {
        val url = UrlBuilder(
                queryParam = mapOf("foo" to "bar")
        ).toString()
        expectThat(url).isEqualTo("http://localhost:8080/?foo=bar")
    }

    @Test
    fun `can build valid url with a bunch of query parameters`() {
        val url = UrlBuilder(
                queryParam = mapOf(
                        "aaa" to "zzz",
                        "bbb" to "yyy",
                        "ccc" to "xxx",
                        "ddd" to "www"
                )
        ).toString()
        expectThat(url).isEqualTo("http://localhost:8080/?aaa=zzz&bbb=yyy&ccc=xxx&ddd=www")
    }
}