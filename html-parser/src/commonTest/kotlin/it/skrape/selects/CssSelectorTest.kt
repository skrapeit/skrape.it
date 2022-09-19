package it.skrape.selects

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test
import kotlin.js.JsName

class CssSelectorTest {

    @JsName("CanCalculateSelectorFromRawCssSelectorParam")
	@Test
	fun `can calculate selector from raw css selector param`() {
        val cssSelector = CssSelector(
                rawCssSelector = "#foo.bar"
        ).toCssSelector
        expect(cssSelector).toEqual("#foo.bar")
    }

    @JsName("CanCalculateSelectorFromRawCssSelectorParamWithSpaces")
	@Test
	fun `can calculate selector from raw css selector param with spaces`() {
        val cssSelector = CssSelector(
                rawCssSelector = "div #foo.bar"
        ).toCssSelector
        expect(cssSelector).toEqual("div #foo.bar")
    }

    @JsName("CanCalculateClassSelectorFromElement")
	@Test
	fun `can calculate class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual(".foo")
    }

    @JsName("CanCalculateListOfClassesSelectorFromElement")
	@Test
	fun `can calculate list of classes selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foo" and "bar"
        ).toCssSelector
        expect(cssSelector).toEqual(".foo.bar")
    }

    @JsName("WhitespacesInClassNamesWillBeKicked")
	@Test
	fun `whitespaces in class names will be kicked`() {
        val cssSelector = CssSelector(
            withClass = "   foobar " and " foo " and " bar "
        ).toCssSelector
        expect(cssSelector).toEqual(".foobar.foo.bar")
    }

    @JsName("CanCalculateSelectorAndBeRelaxedOnMissLeadingSpaces")
	@Test
	fun `can calculate selector and be relaxed on miss-leading spaces`() {
        val cssSelector = CssSelector(
                rawCssSelector = "   div.foo span   ",
                withClass = "   foobar " and " foo " and " bar ",
                withAttribute = "   foooo " to "  bar   ",
                withAttributes = "fizz" to "buzz" and Pair("skrape", "it"),
                withAttributeKeys = listOf("key1", "key2")
        ).toCssSelector
        expect(cssSelector).toEqual("div.foo span.foobar.foo.bar['key1']['key2'][foooo='  bar   '][fizz='buzz'][skrape='it']")
    }

    @JsName("CanCalculateIdSelectorFromElement")
	@Test
	fun `can calculate id selector from element`() {
        val cssSelector = CssSelector(
                withId = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual("#foo")
    }

    @JsName("WhitespacesInIdWillBeKicked")
	@Test
	fun `whitespaces in id will be kicked`() {
        val cssSelector = CssSelector(
            withId = " f o o  "
        ).toCssSelector
        expect(cssSelector).toEqual("#foo")
    }

    @JsName("CanCalculateAttributeKeySelectorFromElement")
	@Test
	fun `can calculate attributeKey selector from element`() {
        val cssSelector = CssSelector(
            withAttributeKey = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual("[foo]")
    }

    @JsName("WhitespacesInAttributeKeyWillBeKickedWithoutValue")
	@Test
	fun `whitespaces in attributeKey will be kicked`() {
        val cssSelector = CssSelector(
            withAttributeKey = " f o o  "
        ).toCssSelector
        expect(cssSelector).toEqual("[foo]")
    }

    @JsName("CanCalculateAttributeSelectorFromElement")
	@Test
	fun `can calculate attribute selector from element`() {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("[foo='bar']")
    }

    @JsName("CanCalculateAttributeSelectorWithAttributeValueContainingWhitespaces")
	@Test
	fun `can calculate attribute selector with attribute value containing whitespaces`() {
        val cssSelector = CssSelector(
            withAttribute = "foo" to " bar foobar  "
        ).toCssSelector
        expect(cssSelector).toEqual("[foo=' bar foobar  ']")
    }

    @JsName("WhitespacesInAttributeKeyWillBeKickedWithValue")
	@Test
	fun `whitespaces in attribute key will be kicked`() {
        val cssSelector = CssSelector(
            withAttribute = "   f o o  " to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("[foo='bar']")
    }

    @JsName("CanCalculateCombinationOfIdAndClassSelectorFromElement")
	@Test
	fun `can calculate combination of id and class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "bar",
                withId = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual("#foo.bar")
    }

    @JsName("CanCalculateCombinationOfIdAndAttributeKeySelectorFromElement")
	@Test
	fun `can calculate combination of id and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "bar",
                withId = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual("#foo[bar]")
    }

    @JsName("CanCalculateCombinationOfIdAndAttributeSelectorFromElement")
	@Test
	fun `can calculate combination of id and attribute selector from element`() {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar",
                withId = "foobar"
        ).toCssSelector
        expect(cssSelector).toEqual("#foobar[foo='bar']")
    }

    @JsName("CanCalculateCombinationOfAttributeAndClassSelectorFromElement")
	@Test
	fun `can calculate combination of attribute and class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual(".foobar[foo='bar']")
    }

    @JsName("CanCalculateCombinationOfAttributeAndAttributeKeySelectorFromElement")
	@Test
	fun `can calculate combination of attribute and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("[foobar][foo='bar']")
    }

    @JsName("CanCalculateCombinationOfIdAttributeKeyAndClassSelectorFromElement")
	@Test
	fun `can calculate combination of id, attributeKey and class selector from element`() {
        val cssSelector = CssSelector(
                withId = "foobar",
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("#foobar.bar[foo]")
    }

    @JsName("CanCalculateCombinationOfIdAttributeAndAttributeKeySelectorFromElement")
	@Test
	fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withId = "fb",
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("#fb[foobar][foo='bar']")
    }

    @JsName("CanCalculateCombinationOfIdAttributeAndClassSelectorFromElement")
	@Test
	fun `can calculate combination of id, attribute and class selector from element`() {
        val cssSelector = CssSelector(
                withId = "fb",
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expect(cssSelector).toEqual("#fb.foobar[foo='bar']")
    }

    @JsName("CanCalculateCombinationOfAttributeKeyAndClassSelectorFromElement")
	@Test
	fun `can calculate combination of attributeKey and class selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        expect(cssSelector).toEqual(".bar[foo]")
    }

    @JsName("CanCalculateComplexCssSelectorFromElement")
	@Test
	fun `can calculate complex css selector from element`() {
        val cssSelector = CssSelector(
                rawCssSelector = "div span a",
                withClass = "bar",
                withId = "foo",
                withAttributeKey = "foobar",
                withAttribute = "fizz" to "buzz"
        ).toCssSelector
        expect(cssSelector).toEqual("div span a#foo.bar[foobar][fizz='buzz']")
    }

    @JsName("ParameterSelectorWillBeMergedWithElementSelector")
	@Test
	fun `parameter selector will be merged with element selector`() {
        val cssSelector = CssSelector(
                withClass = "bar",
                rawCssSelector = "foo"
        ).toCssSelector
        expect(cssSelector).toEqual("foo.bar")
    }

    @JsName("ToStringImplementationReturnsCalculatedCssSelector")
	@Test
	fun `'toString()' implementation returns calculated css selector`() {
        val cssSelector = "${CssSelector(
            rawCssSelector = "div span a",
            withClass = "bar",
            withId = "foo",
            withAttributeKey = "foobar",
            withAttribute = "fizz" to "buzz"
        )}"
        expect(cssSelector).toEqual("div span a#foo.bar[foobar][fizz='buzz']")
    }
}
