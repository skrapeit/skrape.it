package it.skrape.selects.html5

import aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MainRootSelectorsKtTest {

    @Test
    fun `can pick html-tag`() {
        val selector = aValidDocument().html("") {
            findFirst {
                expectThat(attribute("lang")).isEqualTo("en")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("html")
    }
}
