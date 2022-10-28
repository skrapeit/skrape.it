package it.skrape.selects.html5

import aStandardTag
import aValidDocument
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
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
                            expect(text).toEqual("i'm the headline")
                        }
                    }
                }
            }
            customTag("custom-tag") {
                findFirst {
                    expect(text).toEqual("i'm a custom-tag")
                }
                toCssSelector
            }
        }

        expect(selector).toEqual("custom-tag")

    }

    @Test
    @JsName("CanPickHtml5CustomSelectorViaInvokedString")
    fun `can pick html5 custom selector via invoked string`() {
        val selector = aValidDocument(aStandardTag("custom-tag")) {
            "custom-tag" {
                findFirst {
                    expect(text).toEqual("i'm a custom-tag")
                }
                toCssSelector
            }
        }
        expect(selector).toEqual("custom-tag")
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
        expect(selector).toEqual("div#schnitzel bar.foobar")
    }

    @Test
    @JsName("CanCascadeCustomSelectorFromInvokedString")
    fun `can cascade custom selector from invoked string`() {
        expect {
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
        }.toThrow<ElementNotFoundException> { message { toEqual("""Could not find element "div#schnitzel bar.foobar foo[xxx]"""") } }
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
        expect(selector).toContainExactly("div#schnitzel bar.foobar foo[xxx]")
    }
}
