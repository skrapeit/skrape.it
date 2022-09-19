package it.skrape.fetcher

import ch.tutteli.atrium.api.fluent.en_GB.toContainKey
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.fetcher.request.UrlBuilder
import kotlin.js.JsName
import kotlin.test.Test

internal class RequestTest {

    @Test
    @JsName("BodyIsNullByDefault")
    internal fun `body is null by default`() {
        expect(Request().body).toEqual(null)
    }

    @Test
    @JsName("BodyDefaultsToPlainTextMimeTypeAsContentTypeAndEmptyBody")
    internal fun `body defaults to plain text mime type as contentType and empty body`() {
        val request = Request().apply {
            body {}
        }
        expect(request.headers).toContainKey("Content-Type")
        expect(request.headers["Content-Type"]).toEqual("text/plain")
        expect(request.body).toEqual("")
    }

    @Test
    @JsName("CanSetContentTypeAndData")
    internal fun `can set content-type and data`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                data = "bar"
            }
        }
        expect(request.headers["Content-Type"]).toEqual("foo")
        expect(request.body).toEqual("bar")
    }

    @Test
    @JsName("JsonHelperMethodWillSetProperContentTypeAndBody")
    internal fun `json helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                json("""{ "foo": "bar" }""")
            }
        }
        expect(request.headers["Content-Type"]).toEqual("application/json")
        expect(request.body).toEqual("""{ "foo": "bar" }""")
    }

    @Test
    @JsName("XmlHelperMethodWillSetProperContenTypeAndBody")
    internal fun `xml helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                xml("""<foo>bar</foo>""")
            }
        }
        expect(request.headers["Content-Type"]).toEqual("text/xml")
        expect(request.body).toEqual("""<foo>bar</foo>""")
    }

    @Test
    @JsName("CanBuildAndPassJsonOnTheFly")
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
        expect(request.headers["Content-Type"]).toEqual("application/json")
        expect(request.body).toEqual("""{"foo":"bar","xxx":{"a":"b","c":[1,"d"]}}""")
    }

    @Test
    @JsName("FormHelperMethodWillSetProperContentTypeAndBody")
    internal fun `form helper method will set proper content-type and body`() {
        val request = Request().apply {
            body {
                contentType = "foo"
                form("foo=bar&fizz=buzz")
            }
        }
        expect(request.headers["Content-Type"]).toEqual("application/x-www-form-urlencoded")
        expect(request.body).toEqual("foo=bar&fizz=buzz")
    }

    @Test
    @JsName("CanBuildFormBody")
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
        expect(request.headers["Content-Type"]).toEqual("application/x-www-form-urlencoded")
        expect(request.body).toEqual("foo=bar&xxx=true&bar=1.6&yyy=42")
    }

    @Test
    @JsName("CanBuildUrl")
    internal fun `can build url`() {
        val request = Request().apply {
            url {
                protocol = UrlBuilder.Protocol.FTP
            }
        }
        expect(request.url).toEqual("ftp://localhost:8080")
    }

    @Test
    @JsName("UrlBuilderWillRespectRawUrlString")
    internal fun `url builder will respect raw url string`() {
        val request = Request().apply {
            url = "http://www.skrape.it"
            url {
                protocol = UrlBuilder.Protocol.HTTPS
            }
        }
        expect(request.url).toEqual("https://www.skrape.it")
    }

    @Test
    @JsName("UrlBuilderWillRespectRawUrlsQueryParams")
    internal fun `url builder will respect raw urls query params`() {
        val request = Request().apply {
            url = "http://www.skrape.it:1234?foo=bar&xxx"
            url {
                protocol = UrlBuilder.Protocol.HTTPS
                port = 4321
            }
        }
        expect(request.url).toEqual("https://www.skrape.it:4321?foo=bar&xxx")
    }
}
