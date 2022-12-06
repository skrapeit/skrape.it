package it.skrape.fetcher

import io.kotest.matchers.shouldBe
import kotlin.js.JsName
import kotlin.test.Test

class ResultTest {

    private val aValidResult = Result(
            headers = mapOf("X-Foo" to "bar"),
            responseBody = "",
            responseStatus = Result.Status(0, ""),
            contentType = "foo",
            cookies = listOf(
                    "foo=bar".toCookie("skrape.it"),
                    "fizz=buzz".toCookie("google.com")
            )
    )

    @Test
    @JsName("CanGetCookiesFromResultHeaderAsLambda")
	fun `can get cookies from result header as lambda`() {
        aValidResult.cookies {
            this.size.shouldBe(2)
        }
    }

//    @Test
//    @JsName("CanGetCookieByInvokedCookieName")
//	fun `can get cookie by invoked cookie name`() {
//        val first = aValidResult.cookies {
//            "fizz" {
//                expectThat(this).isEqualTo("buzz")
//            }
//        }
//    }
}
