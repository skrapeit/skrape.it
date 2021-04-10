package it.skrape.fetcher

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsKey
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

internal class RequestTest {

    @Test
    internal fun `body is null by default`() {
        expectThat(Request().body).isNull()
    }

    @Test
    internal fun `body defaults to plain text mime type as contentType and empty body`() {
        val request = Request().apply {
            body {}
        }
        expectThat(request.headers).containsKey("Content-Type")
        expectThat(request.headers["Content-Type"]).isEqualTo("text/plain")
        expectThat(request.body).isEqualTo("")
    }

    @Test
    internal fun `can set content-type and data`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                data = "bar"
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("foo")
        expectThat(request.body).isEqualTo("bar")
    }

    @Test
    internal fun `json helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                json("""{ "foo": "bar" }""")
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("application/json")
        expectThat(request.body).isEqualTo("""{ "foo": "bar" }""")
    }

    @Test
    internal fun `xml helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                xml("""<foo>bar</foo>""")
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("text/xml")
        expectThat(request.body).isEqualTo("""<foo>bar</foo>""")
    }

    @Test
    internal fun `can build and pass json on the fly`() {
        val request = Request().apply {
            body {
                json {
                    "foo" to "bar"
                    "xxx" to json {
                        "a" to "b"
                        "c" to listOf(1, "d")
                    }
                }
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("application/json")
        expectThat(request.body).isEqualTo("""{"foo":"bar","xxx":{"a":"b","c":[1,"d"]}}""")
    }

    @Test
    internal fun `form helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                form("foo=bar&fizz=buzz")
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("application/x-www-form-urlencoded")
        expectThat(request.body).isEqualTo("foo=bar&fizz=buzz")
    }

    @Test
    internal fun `can build form body`() {
        val request = Request().apply {
            body {
                form {
                    "foo" to "bar"
                    "xxx" to true
                    "bar" to 1.6
                    "yyy" to 42
                }
            }
        }
        expectThat(request.headers["Content-Type"]).isEqualTo("application/x-www-form-urlencoded")
        expectThat(request.body).isEqualTo("foo=bar&xxx=true&bar=1.6&yyy=42")
    }
}
