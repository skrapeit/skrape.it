package it.skrape.matchers

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ContentTypeHeaderMatchersKtTest : FunSpec({

    withData(
        nameFn = { "can match exact content type ${it.name} from string" },
        ContentTypes.values().toList()
    ) { contentType ->
        val returnedContentTypeValue = contentType.value toBe contentType
        "${contentType.value}foo" toBeNot contentType
        returnedContentTypeValue.shouldBe(contentType.value)
    }

    withData(
        nameFn = { "can match partial content type ${it.name} from string" },
        ContentTypes.values().toList()
    ) { contentType ->
        "${contentType.value}foo" toContain contentType
        "${contentType.value}bar" toContain contentType
    }

})
