package it.skrape.fetcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.fetcher.request.UrlBuilder

class UrlBuilderTest: FunSpec({

    test("can build valid url by default") {
        val url = UrlBuilder().toString()
        url.shouldBe("http://localhost:8080")
    }

    test("can build valid url with defined hostname") {
        val url = UrlBuilder(host = "skrape.it").toString()
        url.shouldBe("http://skrape.it:8080")
    }

    test("can build valid url with defined port") {
        val url = UrlBuilder(port = 8181).toString()
        url.shouldBe("http://localhost:8181")
    }

    test("can build valid url with defined path") {
        val url = UrlBuilder(path = "/foo").toString()
        url.shouldBe("http://localhost:8080/foo")
    }

    test("can build valid url by the use of several options") {
        val url = UrlBuilder().apply {
            protocol = UrlBuilder.Protocol.HTTPS
            path = "/my-path"
            host = "skrape.it"
            port = 12345
            queryParam {
                "skrape" to "it"
            }
        }.toString()
        url.shouldBe("https://skrape.it:12345/my-path?skrape=it")
    }

    test("can build valid url with ONE query parameter") {
        val url = UrlBuilder().apply {
            queryParam {
                "foo" to "bar"
            }
        }.toString()
        url.shouldBe("http://localhost:8080?foo=bar")
    }

    test("can build valid url with a bunch of query parameters") {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to "zzz"
                "bbb" to "yyy"
                "ccc" to "xxx"
                "ddd" to "www"
            }
        }.toString()
        url.shouldBe("http://localhost:8080?aaa=zzz&bbb=yyy&ccc=xxx&ddd=www")
    }

    test("can build valid url with list parameter") {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to listOf("1", 2, .4711, true, null)
                "bbb" to "yyy"
            }
        }.toString()
        url.shouldBe("http://localhost:8080?aaa=1,2,0.4711,true,null&bbb=yyy")
    }

    test("can build valid url with key only parameter shortcut") {
        val url = UrlBuilder().apply {
            queryParam {
                +"aaa"
                "yyy" to "zzz"
                +"bbb"
                +"ccc"
            }
        }.toString()
        url.shouldBe("http://localhost:8080?yyy=zzz&aaa&bbb&ccc")
    }

    xtest("can build valid url with key only parameter") {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to null
                "dfd" to null
            }
        }.toString()
        url.shouldBe("http://localhost:8080?aaa")
    }

    test("can build valid url with fragment") {
        val url = UrlBuilder(fragment = "modal").toString()
        url.shouldBe("http://localhost:8080#modal")
    }

    test("can build valid url with defined protocol") {
        UrlBuilder.Protocol.values().forEach { protocol ->
            val url = UrlBuilder(protocol = protocol).toString()
            url.shouldBe("${protocol.value}://localhost:8080")
        }

    }
})
