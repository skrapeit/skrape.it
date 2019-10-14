package it.skrape.matchers

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ContentTypeHeaderMatchersKtTest {

    @ParameterizedTest
    @EnumSource(ContentTypes::class)
    internal fun `can match exact content types from string`(contentType: ContentTypes) {
        val returnedContentTypeValue = contentType.value `to be` contentType
        "${contentType.value}foo" `to be not` contentType
        expectThat(returnedContentTypeValue).isEqualTo(contentType.value)
    }

    @ParameterizedTest
    @EnumSource(ContentTypes::class)
    internal fun `can match partial content types from string`(contentType: ContentTypes) {
        "${contentType.value}foo" `to contain` contentType
        "${contentType.value}bar" toContain contentType
    }
}
