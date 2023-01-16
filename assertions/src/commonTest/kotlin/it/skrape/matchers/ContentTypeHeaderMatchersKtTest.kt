package it.skrape.matchers

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.ktor.http.*

class ContentTypeHeaderMatchersKtTest : FunSpec({

    val contentTypes = listOf(
        ContentType.Application.JavaScript,
        ContentType.Application.Json,
        ContentType.Audio.MP4,
        ContentType.Video.MP4,
        ContentType.Text.Html
    )

    val contentTypeWildcards = mapOf(
        ContentType.Application.Any to listOf(ContentType.Application.Json, ContentType.Application.GZip),
        ContentType.Audio.Any to listOf(ContentType.Audio.MP4, ContentType.Audio.OGG)
    ).flatMap { entry -> entry.value.map { entry.key to it } }

    withData(
        nameFn = { "can match exact legacy content type $it from string" },
        ContentTypes.values().toList()
    ) { contentType ->
        val returnedContentTypeValue = contentType.value toBe contentType
        "${contentType.value}foo" toBeNot contentType
        returnedContentTypeValue.shouldBe(contentType.value)
    }

    withData(
        nameFn = { "can match partial legacy content type $it from string" },
        ContentTypes.values().toList()
    ) { contentType ->
        "${contentType.value}foo" toContain contentType
        "${contentType.value}bar" toContain contentType
    }

    withData(
        nameFn = { "can match exact content type $it from string" },
        contentTypes
    ) { contentType ->
        val returnedContentTypeValue = contentType.toString() toBe contentType
        "${contentType}foo" toBeNot contentType
        returnedContentTypeValue.shouldBe(contentType.toString())
    }

    withData(
        nameFn = { "can match partial content type $it from string" },
        contentTypes
    ) { contentType ->
        "${contentType}foo" toContain contentType
        "${contentType}bar" toContain contentType
    }

    withData(
        nameFn = { "can match partial wildcard type ${it.first} to ${it.second} " },
        contentTypeWildcards
    ) { contentType ->
        contentType.first toMatch contentType.second
        ContentType.Any toMatch contentType.second
    }

})
