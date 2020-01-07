package it.skrape.core

import it.skrape.core.fetcher.Authentication
import it.skrape.core.fetcher.Request
import it.skrape.core.fetcher.UrlBuilder
import it.skrape.core.fetcher.basic
import it.skrape.skrape
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class RequestTest {

    @Test
    fun `can configure basic auth`() {
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

    @Test
    fun `can build url via dsl`() {
        val client = skrape {
            url = urlBuilder {
                protocol = UrlBuilder.Protocol.HTTPS
                port = 12345
                path = "/foo"
                queryParam = mapOf("foo" to "bar", "fizz" to "buzz")
                host = "foo.com"
            }
            this
        }

        expectThat(client.url).isEqualTo("https://foo.com:12345/foo?foo=bar&fizz=buzz")
    }
}
