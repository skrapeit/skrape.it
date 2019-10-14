package it.skrape.selects.html5

import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MainRootElementPickersKtTest {

    @Test
    internal fun `can pick html-tag`() {
        aValidDocument().html("") {
            expectThat(attr("lang")).isEqualTo("en")
        }
    }
}
