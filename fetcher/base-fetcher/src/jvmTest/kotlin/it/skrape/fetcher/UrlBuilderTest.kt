package it.skrape.fetcher

import it.skrape.fetcher.request.UrlBuilder
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class UrlBuilderTest {

    @Test
    fun `can build valid url by default`() {
        val url = UrlBuilder().toString()
        expectThat(url).isEqualTo("http://localhost:8080")
    }

    @ParameterizedTest(name = "can build valid {0}-Url if protocol param is defined")
    @EnumSource(UrlBuilder.Protocol::class)
    fun `can build valid url with defined protocol`(protocol: UrlBuilder.Protocol) {
        val url = UrlBuilder(protocol = protocol).toString()
        expectThat(url).isEqualTo("${protocol.value}://localhost:8080")
    }

    @Test
    fun `can build valid url with defined hostname`() {
        val url = UrlBuilder(host = "skrape.it").toString()
        expectThat(url).isEqualTo("http://skrape.it:8080")
    }

    @Test
    fun `can build valid url with defined port`() {
        val url = UrlBuilder(port = 8181).toString()
        expectThat(url).isEqualTo("http://localhost:8181")
    }

    @Test
    fun `can build valid url with defined path`() {
        val url = UrlBuilder(path = "/foo").toString()
        expectThat(url).isEqualTo("http://localhost:8080/foo")
    }

    @Test
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
        expectThat(url).isEqualTo("https://skrape.it:12345/my-path?skrape=it")
    }

    @Test
    fun `can build valid url with ONE query parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "foo" to "bar"
            }
        }.toString()
        expectThat(url).isEqualTo("http://localhost:8080?foo=bar")
    }

    @Test
    fun `can build valid url with a bunch of query parameters`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to "zzz"
                "bbb" to "yyy"
                "ccc" to "xxx"
                "ddd" to "www"
            }
        }.toString()
        expectThat(url).isEqualTo("http://localhost:8080?aaa=zzz&bbb=yyy&ccc=xxx&ddd=www")
    }

    @Test
    fun `can build valid url with list parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to listOf("1", 2, .4711, true, null)
                "bbb" to "yyy"
            }
        }.toString()
        expectThat(url).isEqualTo("http://localhost:8080?aaa=1,2,0.4711,true,null&bbb=yyy")
    }

    @Test
    fun `can build valid url with key only parameter shortcut`() {
        val url = UrlBuilder().apply {
            queryParam {
                +"aaa"
                "yyy" to "zzz"
                +"bbb"
                +"ccc"
            }
        }.toString()
        expectThat(url).isEqualTo("http://localhost:8080?yyy=zzz&aaa&bbb&ccc")
    }

    @Test
    @Disabled("currently null values are ignored")
    fun `can build valid url with key only parameter`() {
        val url = UrlBuilder().apply {
            queryParam {
                "aaa" to null
                "dfd" to null
            }
        }.toString()
        expectThat(url).isEqualTo("http://localhost:8080?aaa")
    }

    @Test
    fun `can build valid url with fragment`() {
        val url = UrlBuilder(fragment = "modal").toString()
        expectThat(url).isEqualTo("http://localhost:8080#modal")
    }
}
