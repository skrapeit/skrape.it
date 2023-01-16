package it.skrape.matchers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.ktor.http.*

class ContentTypeHeaderMatchersKtTest : FunSpec({

    val contentTypeWildcards = mapOf(
        ContentType.Application.Any to listOf(ContentType.Application.Json, ContentType.Application.GZip),
        ContentType.Audio.Any to listOf(ContentType.Audio.MP4, ContentType.Audio.OGG)
    ).flatMap { entry -> entry.value.map { entry.key to it } }

    withData(
        nameFn = { "can match partial wildcard type ${it.first} to ${it.second} " },
        contentTypeWildcards
    ) { contentType ->
        contentType.first toMatch contentType.second
        ContentType.Any toMatch contentType.second
    }

    test("does not match on null") {
        null notToBe ContentType.Any
        ContentType.Any notToBe null
    }

    test("will not match nulls") {
        shouldThrow<AssertionError> {
            null toBeExactly ContentType.Any
        }
        shouldThrow<AssertionError> {
            null toMatch ContentType.Any
        }
        shouldThrow<AssertionError> {
            ContentType.Any toBeExactly null
        }
        shouldThrow<AssertionError> {
            ContentType.Any toMatch null
        }
    }

})
