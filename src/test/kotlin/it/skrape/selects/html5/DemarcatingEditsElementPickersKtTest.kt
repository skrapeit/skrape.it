package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidHtml
import it.skrape.aValidResult
import org.junit.jupiter.api.Test

import java.util.*

internal class DemarcatingEditsElementPickersKtTest {

    @Test
    fun `can parse del-tag`() {
        val result = aValidResult(aStandardTag("del"))
        result.del {
            assertThat(text()).isEqualTo("i'm a del")
        }
    }

    @Test
    fun `can parse ins-tag`() {
        val result = aValidResult(aStandardTag("ins"))
        result.ins {
            assertThat(text()).isEqualTo("i'm a ins")
        }
    }
}