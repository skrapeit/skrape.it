package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.extract
import it.skrape.skrape
import org.junit.jupiter.api.Test

internal class HtmlExtractorsTest : WireMockSetup() {

    @Test
    internal fun `can pick html via html function`() {
        wireMockServer.setupStub()

        skrape {
            extract {
                html {
                    assertThat(attr("lang")).isEqualTo("en")
                }
            }
        }
    }
}
