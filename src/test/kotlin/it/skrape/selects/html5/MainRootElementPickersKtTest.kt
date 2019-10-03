package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainRootElementPickersKtTest {

    @Test
    internal fun `can pick html-tag`() {
        aValidResult().html {
            assertThat(attr("lang")).isEqualTo("en")
        }
    }
}
