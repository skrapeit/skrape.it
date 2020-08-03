package it.skrape.core.fetcher

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@Execution(CONCURRENT)
class ResultTest {

    private val aValidResult = Result(
            request = Request(),
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
    fun `can get cookies from result header as lambda`() {
        aValidResult.cookies {
            expectThat(this.size).isEqualTo(2)
        }
    }

//    @Test
//    fun `can get cookie by invoked cookie name`() {
//        val first = aValidResult.cookies {
//            "fizz" {
//                expectThat(this).isEqualTo("buzz")
//            }
//        }
//    }
}
