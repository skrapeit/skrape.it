package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import it.skrape.fetcher.Result

class StatusMatchersKtTest : FunSpec({

    fun filteredHttpStatus(filter: String) = HttpStatus.values().filter { it.name.matches(filter.toRegex()) }

    withData(nameFn = { "Can match exact ${it.name}" }, filteredHttpStatus("^((?!xx).)*\$")) { httpStatus ->
        val returnedContentTypeValue = Result.Status(httpStatus.code, httpStatus.message) toBe httpStatus
        returnedContentTypeValue.copy(code = 999) toBeNot httpStatus
        returnedContentTypeValue.copy(message = "xxx") toBeNot httpStatus
        returnedContentTypeValue.shouldBe(httpStatus.toStatus())
    }

    withData(nameFn = { "will throw exception for non matching ${it.name} status code" },
        filteredHttpStatus("^((?!xx).)*\$")) { httpStatus ->
        shouldThrow<AssertionError> {
            Result.Status(httpStatus.code + 1, httpStatus.message) toBe httpStatus
        }
    }

    withData(nameFn = { "will throw exception for non matching ${it.name} status message" },
        filteredHttpStatus("^((?!xx).)*\$")) { httpStatus ->
        shouldThrow<AssertionError> {
            Result.Status(httpStatus.code, httpStatus.message + "foo") toBe httpStatus
        }
    }

    withData(nameFn = { "can match ${it.name} status code by first digit and ignoring message" },
        filteredHttpStatus(".xx.*")) { httpStatus ->
        Result.Status(httpStatus.code * 101, "xxx") toBe httpStatus
        Result.Status(httpStatus.code * -1, "xxx") toBeNot httpStatus
    }

})
