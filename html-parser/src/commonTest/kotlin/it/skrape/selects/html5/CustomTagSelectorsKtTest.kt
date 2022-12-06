package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import it.skrape.selects.ElementNotFoundException
import kotlin.js.JsName
import kotlin.test.Test

class CustomTagSelectorsKtTest {

    @Test
    @JsName("CanPickHtml5CustomTag")
    fun `can pick html5 custom-tag`() {
        val selector = aValidDocument(aStandardTag("custom-tag")) {
            customTag("header") {
                findFirst {
                    customTag("h1") {
                        findFirst {
                            text.shouldBe("i'm the headline")
                        }
                    }
                }
            }
            customTag("custom-tag") {
                findFirst {
                    text.shouldBe("i'm a custom-tag")
                }
                toCssSelector
            }
        }

        selector.shouldBe("custom-tag")

    }

    @Test
    @JsName("CanPickHtml5CustomSelectorViaInvokedString")
    fun `can pick html5 custom selector via invoked string`() {
        val selector = aValidDocument(aStandardTag("custom-tag")) {
            "custom-tag" {
                findFirst {
                    text.shouldBe("i'm a custom-tag")
                }
                toCssSelector
            }
        }
        selector.shouldBe("custom-tag")
    }

    @Test
    @JsName("CanCascadeCustomTagSelectors")
    fun `can cascade custom tag selectors`() {
        val selector = aValidDocument {
            customTag("div") {
                withId = "schnitzel"
                customTag("bar") {
                    withClass = "foobar"
                    toCssSelector
                }
            }
        }
        selector.shouldBe("div#schnitzel bar.foobar")
    }

    @Test
    @JsName("CanCascadeCustomSelectorFromInvokedString")
    fun `can cascade custom selector from invoked string`() {
        shouldThrowWithMessage<ElementNotFoundException>("""Could not find element "div#schnitzel bar.foobar foo[xxx]"""") {
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
    }

    @Test
    @JsName("CascadingCustomSelectorWillReturnGenericType")
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
        selector.shouldContainExactly("div#schnitzel bar.foobar foo[xxx]")
    }
}
