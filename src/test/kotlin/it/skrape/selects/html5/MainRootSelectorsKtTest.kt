package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.selects.findFirst
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MainRootSelectorsKtTest {

    @Test
    internal fun `can pick html-tag`() {
        val selector = aValidDocument().html("") {
            findFirst {
                expectThat(attr("lang")).isEqualTo("en")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("html")
    }
}
