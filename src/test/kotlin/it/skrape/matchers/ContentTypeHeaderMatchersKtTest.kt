package it.skrape.matchers

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ContentTypeHeaderMatchersKtTest {

    @ParameterizedTest
    @EnumSource(ContentTypes::class)
    internal fun `can match exact content types from string`(contentType: ContentTypes) {
        contentType.value `to be` contentType
        "${contentType.value}foo" `to be not` contentType
    }

    @ParameterizedTest
    @EnumSource(ContentTypes::class)
    internal fun `can match partial content types from string`(contentType: ContentTypes) {
        "${contentType.value}foo" `to contain` contentType
        "${contentType.value}bar" toContain contentType
    }
}
