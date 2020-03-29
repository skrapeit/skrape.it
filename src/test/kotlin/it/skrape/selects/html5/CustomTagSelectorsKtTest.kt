package it.skrape.selects.html5

import it.skrape.aStandardTag
import it.skrape.aValidDocument
import it.skrape.exceptions.ElementNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo

internal class CustomTagSelectorsKtTest {

    @Test
    internal fun `can pick html5 custom-tag`() {
        val selector = aValidDocument(aStandardTag("custom-tag")).customTag("custom-tag") {
            findFirst {
                expectThat(text).isEqualTo("i'm a custom-tag")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("custom-tag")
    }

    @Test
    internal fun `can pick html5 custom selector via invoked string`() {
        val selector = aValidDocument(aStandardTag("custom-tag")) {
            "custom-tag" {
                findFirst {
                    expectThat(text).isEqualTo("i'm a custom-tag")
                }
                toCssSelector
            }
        }
        expectThat(selector).isEqualTo("custom-tag")
    }

    @Test
    internal fun `can cascade custom tag selectors`() {
        val selector = aValidDocument {
            customTag("div") {
                withId = "schnitzel"
                customTag("bar") {
                    withClass = "foobar"
                    toCssSelector
                }
            }
        }
        expectThat(selector).isEqualTo("div#schnitzel bar.foobar")
    }

    @Test
    fun `can cascade custom selector from invoked string`() {
        val error = Assertions.assertThrows(ElementNotFoundException::class.java) {
            aValidDocument {
                "div" {
                    withId = "schnitzel"
                    "bar" {
                        withClass = "foobar"
                        "foo" {
                            withAttributeKey = "xxx"
                            toCssSelector
                            findFirst {}
                        }
                    }
                }
            }
        }
        expectThat(error.message).isEqualTo("""Could not find element "div#schnitzel bar.foobar foo[xxx]"""")
    }

    @Test
    fun `cascading custom selector will return generic type`() {
        val selector = aValidDocument {
            "div" {
                withId = "schnitzel"
                "bar" {
                    withClass = "foobar"
                    "foo" {
                        withAttributeKey = "xxx"
                        listOf(toCssSelector)
                    }
                }
            }
        }
        expectThat(selector).containsExactly("div#schnitzel bar.foobar foo[xxx]")
    }
}
