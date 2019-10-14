package it.skrape.selects.html5

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aStandardTag
import it.skrape.aValidDocument
import org.junit.jupiter.api.Test

internal class DemarcatingEditsElementPickersKtTest {

    @Test
    fun `can parse del-tag`() {
        val doc = aValidDocument(aStandardTag("del"))
        doc.del {
            assertThat(text()).isEqualTo("i'm a del")
        }
    }

    @Test
    fun `can parse ins-tag`() {
        val doc = aValidDocument(aStandardTag("ins"))
        doc.ins {
            assertThat(text()).isEqualTo("i'm a ins")
        }
    }
}