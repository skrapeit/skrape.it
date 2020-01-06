package it.skrape.core

import it.skrape.core.fetcher.Authentication
import it.skrape.core.fetcher.Protocol
import it.skrape.core.fetcher.Protocol.*
import it.skrape.core.fetcher.Request
import it.skrape.core.fetcher.basic
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class RequestTest {

    @Test
    internal fun `can build valid url by default`() {
        val url = Request().url
        expectThat(url).isEqualTo("http://localhost:8080/")
    }

    @ParameterizedTest(name = "can build valid {0}-Url if protocol param is defined")
    @EnumSource(Protocol::class)
    internal fun `can build valid url with defined protocol`(protocol: Protocol) {
        val url = Request(protocol = protocol).url
        expectThat(url).isEqualTo("${protocol.value}localhost:8080/")
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

    @Test
    internal fun `can build valid url by the use of several options`() {
        val url = Request(
                protocol = HTTPS,
                path = "/my-path",
                host = "skrape.it",
                port = 12345,
                queryParam = mapOf("skrape" to "it")
        ).url
        expectThat(url).isEqualTo("https://skrape.it:12345/my-path?skrape=it")
    }

    @Test
    internal fun `can build valid url with ONE query parameter`() {
        val url = Request(
                queryParam = mapOf("foo" to "bar")
        ).url
        expectThat(url).isEqualTo("http://localhost:8080/?foo=bar")
    }

    @Test
    internal fun `can build valid url with a bunch of query parameters`() {
        val url = Request(
                queryParam = mapOf(
                        "aaa" to "zzz",
                        "bbb" to "yyy",
                        "ccc" to "xxx",
                        "ddd" to "www"
                )
        ).url
        expectThat(url).isEqualTo("http://localhost:8080/?aaa=zzz&bbb=yyy&ccc=xxx&ddd=www")
    }

    @Test
    internal fun `can configure basic auth`() {
        val auth = Request(
                authentication = basic {
                    username = "my-username"
                    password = "my-password"
                }
        ).authentication
        expectThat(auth.type).isEqualTo(Authentication.Type.BASIC)
        expectThat(auth.username).isEqualTo("my-username")
        expectThat(auth.password).isEqualTo("my-password")
    }
}
