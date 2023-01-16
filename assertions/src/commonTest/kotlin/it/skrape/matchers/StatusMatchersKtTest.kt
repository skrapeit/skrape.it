package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.ktor.http.*

class StatusMatchersKtTest : FunSpec({


    withData(nameFn = { "Can match exact ${it.description}" }, HttpStatusCode.allStatusCodes) { httpStatus ->
        val returnedContentTypeValue = HttpStatusCode(httpStatus.value, httpStatus.description) toBe httpStatus
        returnedContentTypeValue.copy(value = 999) notToBeExactly  httpStatus
        returnedContentTypeValue.copy(description = "xxx") notToBeExactly httpStatus
        returnedContentTypeValue.shouldBe(httpStatus)
    }

    withData(nameFn = { "will throw exception for non matching ${it.description} status code" },
        HttpStatusCode.allStatusCodes) { httpStatus ->
        shouldThrow<AssertionError> {
            HttpStatusCode(httpStatus.value + 1, httpStatus.description) toBe httpStatus
        }
    }

    withData(nameFn = { "will throw exception for non matching ${it.description} status message" },
        HttpStatusCode.allStatusCodes) { httpStatus ->
        shouldThrow<AssertionError> {
            HttpStatusCode(httpStatus.value, httpStatus.description + "foo") toBe httpStatus
        }
    }

    withData(nameFn = { "can match ${it.description} status code by first digit and ignoring message" },
        HttpStatusCode.allGroupStatusCodes) { httpStatus ->
        HttpStatusCode(httpStatus.value * 101, "xxx") toBe httpStatus
        HttpStatusCode(httpStatus.value * -1, "xxx") notToBeExactly httpStatus
    }

})
