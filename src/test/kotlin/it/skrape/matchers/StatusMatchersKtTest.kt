package it.skrape.matchers

import it.skrape.core.fetcher.Result
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ANY
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class StatusMatchersKtTest {

    @ParameterizedTest(name = "can match http status {0}")
    @EnumSource(
            value = HttpStatus::class,
            mode = MATCH_ANY,
            names = ["^((?!xx).)*\$"]
    )
    fun `can match exact http status`(httpStatus: HttpStatus) {
        val returnedContentTypeValue = Result.Status(httpStatus.code, httpStatus.message) toBe httpStatus
        returnedContentTypeValue.copy(code = 999) toBeNot httpStatus
        returnedContentTypeValue.copy(message = "xxx") toBeNot httpStatus
        expectThat(returnedContentTypeValue).isEqualTo(httpStatus.toStatus())
    }

    @ParameterizedTest(name = "throw error on non matching http status {0} code")
    @EnumSource(
            value = HttpStatus::class,
            mode = MATCH_ANY,
            names = ["^((?!xx).)*\$"]
    )
    fun `will throw exception for none matching status codes`(httpStatus: HttpStatus) {
        Assertions.assertThrows(AssertionError::class.java) {
            Result.Status(httpStatus.code + 1, httpStatus.message) toBe httpStatus
        }
    }

    @ParameterizedTest(name = "throw error on non matching http status {0} message")
    @EnumSource(
            value = HttpStatus::class,
            mode = MATCH_ANY,
            names = ["^((?!xx).)*\$"]
    )
    fun `will throw exception for none matching status message`(httpStatus: HttpStatus) {
        Assertions.assertThrows(AssertionError::class.java) {
            Result.Status(httpStatus.code, httpStatus.message + "foo") toBe httpStatus
        }
    }

    @ParameterizedTest(name = "can match {0} status codes by its first digit and ignoring message")
    @EnumSource(
            value = HttpStatus::class,
            mode = MATCH_ANY,
            names = [".xx.*"]
    )
    fun `can match status code by first digit and ignoring message`(httpStatus: HttpStatus) {
        Result.Status(httpStatus.code * 101, "xxx") toBe httpStatus
        Result.Status(httpStatus.code * -1, "xxx") toBeNot httpStatus
    }

}
