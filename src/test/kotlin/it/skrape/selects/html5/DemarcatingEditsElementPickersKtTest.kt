package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidHtml
import it.skrape.aValidResult
import org.junit.jupiter.api.Test

import java.util.*

internal class DemarcatingEditsElementPickersKtTest {

    @Test
    fun `can parse del-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<del>$uniqueString</del>"))
        result.del {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }

    @Test
    fun `can parse ins-tag`() {
        val uniqueString = UUID.randomUUID().toString()
        val result = aValidResult(aValidHtml("<ins>$uniqueString</ins>"))
        result.ins {
            assertThat(text()).isEqualTo(uniqueString)
        }
    }
}