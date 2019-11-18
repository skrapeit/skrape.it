package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class CustomTagSelectorsKtTest {

    @Test
    internal fun `can pick html5 custom-tag`() {
        val selector = aValidDocument(aStandardTag("custom-tag")).customTag("custom-tag") {
            findFirst {
                expectThat(text).isEqualTo("i'm a custom-tag")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("custom-tag")
    }
}
