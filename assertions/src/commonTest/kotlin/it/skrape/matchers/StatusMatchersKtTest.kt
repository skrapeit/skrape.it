package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import it.skrape.fetcher.Result
import kotlin.js.JsName
import kotlin.test.Test

class StatusMatchersKtTest {

    inline fun <reified T: Enum<T>> testParameterized(matcher: String, function: (T)->Unit) = testParameterized(matcher.toRegex(), function)
    inline fun <reified T: Enum<T>> testParameterized(matcher: Regex, function: (T)->Unit) {
        enumValues<T>().filter { it.name.matches(matcher) }.forEach(function)
    }

    @JsName("CanMatchExactHttpStatus")
	@Test
	fun `can match exact http status`() = testParameterized<HttpStatus>("^((?!xx).)*\$", ::matchExactTest)

    fun matchExactTest(httpStatus: HttpStatus) {
        val returnedContentTypeValue = Result.Status(httpStatus.code, httpStatus.message) toBe httpStatus
        returnedContentTypeValue.copy(code = 999) toBeNot httpStatus
        returnedContentTypeValue.copy(message = "xxx") toBeNot httpStatus
        returnedContentTypeValue.shouldBe(httpStatus.toStatus())
    }

    @JsName("WillThrowExceptionForNoneMatchingStatusCodes")
	@Test
	fun `will throw exception for none matching status codes`() = testParameterized<HttpStatus>("^((?!xx).)*\$", ::matchNoneCodeTest)
    
    fun matchNoneCodeTest(httpStatus: HttpStatus) {
        shouldThrow<AssertionError> {
            Result.Status(httpStatus.code + 1, httpStatus.message) toBe httpStatus
        }
    }

    @JsName("WillThrowExceptionForNoneMatchingStatusMessage")
	@Test
	fun `will throw exception for none matching status message`() = testParameterized<HttpStatus>("^((?!xx).)*\$", ::matchNoneStatusTest)

    fun matchNoneStatusTest(httpStatus: HttpStatus) {
        shouldThrow<AssertionError> {
            Result.Status(httpStatus.code, httpStatus.message + "foo") toBe httpStatus
        }
    }

    @JsName("CanMatchStatusCodeByFirstDigitAndIgnoringMessage")
	@Test
	fun `can match status code by first digit and ignoring message`() = testParameterized<HttpStatus>(".xx.*", ::matchCodeFirstDigitTest)

    fun matchCodeFirstDigitTest(httpStatus: HttpStatus) {
        Result.Status(httpStatus.code * 101, "xxx") toBe httpStatus
        Result.Status(httpStatus.code * -1, "xxx") toBeNot httpStatus
    }

}
