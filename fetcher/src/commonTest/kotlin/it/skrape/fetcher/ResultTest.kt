package it.skrape.fetcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.http.*

class ResultTest: FunSpec({

    val aValidResult = Result(
            headers = headersOf("X-Foo" to listOf("bar")),
            responseBody = "",
            responseStatus = Result.Status(0, ""),
            contentType = "foo",
            cookies = listOf(
                    "foo=bar".toCookie("skrape.it"),
                    "fizz=buzz".toCookie("google.com")
            )
    )

    test("can get cookies from result header as lambda") {
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
})
