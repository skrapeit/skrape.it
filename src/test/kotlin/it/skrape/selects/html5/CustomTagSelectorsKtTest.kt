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

    @Test
    internal fun `can pick html5 custom selector via invoked string`() {
        val selector = aValidDocument {
           "custom-tag" {
                findFirst {
                    expectThat(text).isEqualTo("i'm a custom-tag")
                }
                rawCssSelector
            }
        }
        expectThat(selector).isEqualTo("custom-tag")
    }

    @Test
    internal fun `can cascade custom selector from invoked string`() {
        val selector = aValidDocument {
            "foo" {
                withId = "schnitzel"
                "bar" {
                    withClass = "foobar"
                    rawCssSelector
                }
            }
        }
        expectThat(selector).isEqualTo("foo#schnitzel bar.foobar")
    }
}
