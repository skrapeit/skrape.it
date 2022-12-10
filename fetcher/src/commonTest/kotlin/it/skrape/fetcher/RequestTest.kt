package it.skrape.fetcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*

internal class RequestTest: FunSpec({

    test("body is null by default") {
        KtorRequestBuilder().body.shouldBe(EmptyContent)
    }

    test("body defaults to plain text mime type as contentType and empty body") {
        val request = KtorRequestBuilder().apply {
            body {}
        }
        request.headers.contains("Content-Type").shouldBeTrue()
        request.headers["Content-Type"].shouldBe("text/plain")
        request.body.shouldBe("")
    }

    test("can set content-type and data") {
        val request = KtorRequestBuilder().apply {
            body {
                contentType = "text/plain"
                data = "bar"
            }
        }
        request.headers["Content-Type"].shouldBe("text/plain")
        request.body.shouldBe("bar")
    }

    test("json helper method will set proper content-type and body") {
        val request = KtorRequestBuilder().apply {
            body {
                contentType = "foo"
                json("""{ "foo": "bar" }""")
            }
        }
        request.headers["Content-Type"].shouldBe("application/json")
        request.body.shouldBe("""{ "foo": "bar" }""")
    }

    test("xml helper method will set proper content-type and body") {
        val request = KtorRequestBuilder().apply {
            body {
                contentType = "foo"
                xml("""<foo>bar</foo>""")
            }
        }
        request.headers["Content-Type"].shouldBe("text/xml")
        request.body.shouldBe("""<foo>bar</foo>""")
    }

    test("can build and pass json on the fly") {
        val request = KtorRequestBuilder().apply {
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
        request.headers["Content-Type"].shouldBe("application/json")
        request.body.shouldBe("""{"foo":"bar","xxx":{"a":"b","c":[1,"d"]}}""")
    }

    test("form helper method will set proper content-type and body") {
        val request = KtorRequestBuilder().apply {
            body {
                contentType = "foo"
                form("foo=bar&fizz=buzz")
            }
        }
        request.headers["Content-Type"].shouldBe("application/x-www-form-urlencoded")
        request.body.shouldBe("foo=bar&fizz=buzz")
    }

    test("can build form body") {
        val request = KtorRequestBuilder().apply {
            body {
                form {
                    "foo" to "bar"
                    "xxx" to true
                    "bar" to 1.6
                    "yyy" to 42
                }
            }
        }
        request.headers["Content-Type"].shouldBe("application/x-www-form-urlencoded")
        request.body.shouldBe("foo=bar&xxx=true&bar=1.6&yyy=42")
    }

    test("can build url") {
        val request = KtorRequestBuilder().apply {
            url {
                protocol = URLProtocol("ftp", 53)
                port = 8080
            }
        }
        request.url.buildString().shouldBe("ftp://localhost:8080")
    }

    test("url builder will respect raw url string") {
        val request = KtorRequestBuilder().apply {
            url("http://www.skrape.it")
            url {
                protocol = URLProtocol.HTTPS
            }
        }
        request.url.buildString().shouldBe("https://www.skrape.it")
    }

    test("url builder will respect raw urls query params") {
        val request = KtorRequestBuilder().apply {
            url("http://www.skrape.it:1234?foo=bar&xxx")
            url {
                protocol = URLProtocol.HTTPS
                port = 4321
            }
        }
        request.url.buildString().shouldBe("https://www.skrape.it:4321?foo=bar&xxx")
    }
})
