package it.skrape.fetcher

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.fetcher.request.UrlBuilder
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.Ignore

class UrlBuilderTest {

    @Test
    @JsName("CanBuildValidUrlByDefault")
	fun `can build valid url by default`() {
        val url = UrlBuilder().toString()
        expect(url).toEqual("http://localhost:8080")
    }

    @Test
    @JsName("CanBuildValidUrlWithDefinedHostname")
	fun `can build valid url with defined hostname`() {
        val url = UrlBuilder(host = "skrape.it").toString()
        expect(url).toEqual("http://skrape.it:8080")
    }

    @Test
    @JsName("CanBuildValidUrlWithDefinedPort")
	fun `can build valid url with defined port`() {
        val url = UrlBuilder(port = 8181).toString()
        expect(url).toEqual("http://localhost:8181")
    }

    @Test
    @JsName("CanBuildValidUrlWithDefinedPath")
	fun `can build valid url with defined path`() {
        val url = UrlBuilder(path = "/foo").toString()
        expect(url).toEqual("http://localhost:8080/foo")
    }

    @Test
    @JsName("CanBuildValidUrlByTheUseOfSeveralOptions")
	fun `can build valid url by the use of several options`() {
        val url = UrlBuilder().apply {
            protocol = UrlBuilder.Protocol.HTTPS
            path = "/my-path"
            host = "skrape.it"
            port = 12345
            queryParam {
                "skrape" to "it"
            }
        }.toString()
        expect(url).toEqual("https://skrape.it:12345/my-path?skrape=it")
    }

    @Test
    @JsName("CanBuildValidUrlWithONEQueryParameter")
	fun `can build valid url with ONE query parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "foo" to "bar"
            }
        }.toString()
        expect(url).toEqual("http://localhost:8080?foo=bar")
    }

    @Test
    @JsName("CanBuildValidUrlWithABunchOfQueryParameters")
	fun `can build valid url with a bunch of query parameters`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to "zzz"
                "bbb" to "yyy"
                "ccc" to "xxx"
                "ddd" to "www"
            }
        }.toString()
        expect(url).toEqual("http://localhost:8080?aaa=zzz&bbb=yyy&ccc=xxx&ddd=www")
    }

    @Test
    @JsName("CanBuildValidUrlWithListParameter")
	fun `can build valid url with list parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to listOf("1", 2, .4711, true, null)
                "bbb" to "yyy"
            }
        }.toString()
        expect(url).toEqual("http://localhost:8080?aaa=1,2,0.4711,true,null&bbb=yyy")
    }

    @Test
    @JsName("CanBuildValidUrlWithKeyOnlyParameterShortcut")
	fun `can build valid url with key only parameter shortcut`() {
        val url = UrlBuilder().apply {
            queryParam {
                +"aaa"
                "yyy" to "zzz"
                +"bbb"
                +"ccc"
            }
        }.toString()
        expect(url).toEqual("http://localhost:8080?yyy=zzz&aaa&bbb&ccc")
    }

    @Test
    @Ignore
    @JsName("CanBuildValidUrlWithKeyOnlyParameter")
	fun `can build valid url with key only parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to null
                "dfd" to null
            }
        }.toString()
        expect(url).toEqual("http://localhost:8080?aaa")
    }

    @Test
    @JsName("CanBuildValidUrlWithFragment")
	fun `can build valid url with fragment`() {
        val url = UrlBuilder(fragment = "modal").toString()
        expect(url).toEqual("http://localhost:8080#modal")
    }

    @Test
    @JsName("CanBuildValidUrlWithDefinedProtocol")
	fun `can build valid url with defined protocol`() {
        UrlBuilder.Protocol.values().forEach { protocol ->
            val url = UrlBuilder(protocol = protocol).toString()
            expect(url).toEqual("${protocol.value}://localhost:8080")
        }

    }
}
