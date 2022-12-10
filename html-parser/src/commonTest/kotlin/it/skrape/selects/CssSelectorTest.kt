package it.skrape.selects

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CssSelectorTest: FunSpec({

    test("can calculate selector from raw css selector param") {
        val cssSelector = CssSelector(
                rawCssSelector = "#foo.bar"
        ).toCssSelector
        cssSelector.shouldBe("#foo.bar")
    }

    test("can calculate selector from raw css selector param with spaces") {
        val cssSelector = CssSelector(
                rawCssSelector = "div #foo.bar"
        ).toCssSelector
        cssSelector.shouldBe("div #foo.bar")
    }

    test("can calculate class selector from element") {
        val cssSelector = CssSelector(
                withClass = "foo"
        ).toCssSelector
        cssSelector.shouldBe(".foo")
    }

    test("can calculate list of classes selector from element") {
        val cssSelector = CssSelector(
                withClass = "foo" and "bar"
        ).toCssSelector
        cssSelector.shouldBe(".foo.bar")
    }

    test("whitespaces in class names will be kicked") {
        val cssSelector = CssSelector(
            withClass = "   foobar " and " foo " and " bar "
        ).toCssSelector
        cssSelector.shouldBe(".foobar.foo.bar")
    }

    test("can calculate selector and be relaxed on miss-leading spaces") {
        val cssSelector = CssSelector(
                rawCssSelector = "   div.foo span   ",
                withClass = "   foobar " and " foo " and " bar ",
                withAttribute = "   foooo " to "  bar   ",
                withAttributes = "fizz" to "buzz" and Pair("skrape", "it"),
                withAttributeKeys = listOf("key1", "key2")
        ).toCssSelector
        cssSelector.shouldBe("div.foo span.foobar.foo.bar[key1][key2][foooo='  bar   '][fizz='buzz'][skrape='it']")
    }

    test("can calculate id selector from element") {
        val cssSelector = CssSelector(
                withId = "foo"
        ).toCssSelector
        cssSelector.shouldBe("#foo")
    }

    test("whitespaces in id will be kicked") {
        val cssSelector = CssSelector(
            withId = " f o o  "
        ).toCssSelector
        cssSelector.shouldBe("#foo")
    }

    test("can calculate attributeKey selector from element") {
        val cssSelector = CssSelector(
            withAttributeKey = "foo"
        ).toCssSelector
        cssSelector.shouldBe("[foo]")
    }

    test("whitespaces in attributeKey will be kicked") {
        val cssSelector = CssSelector(
            withAttributeKey = " f o o  "
        ).toCssSelector
        cssSelector.shouldBe("[foo]")
    }

    test("can calculate attribute selector from element") {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar"
        ).toCssSelector
        cssSelector.shouldBe("[foo='bar']")
    }

    test("can calculate attribute selector with attribute value containing whitespaces") {
        val cssSelector = CssSelector(
            withAttribute = "foo" to " bar foobar  "
        ).toCssSelector
        cssSelector.shouldBe("[foo=' bar foobar  ']")
    }

    test("whitespaces in attribute key will be kicked") {
        val cssSelector = CssSelector(
            withAttribute = "   f o o  " to "bar"
        ).toCssSelector
        cssSelector.shouldBe("[foo='bar']")
    }

    test("can calculate combination of id and class selector from element") {
        val cssSelector = CssSelector(
                withClass = "bar",
                withId = "foo"
        ).toCssSelector
        cssSelector.shouldBe("#foo.bar")
    }

    test("can calculate combination of id and attributeKey selector from element") {
        val cssSelector = CssSelector(
                withAttributeKey = "bar",
                withId = "foo"
        ).toCssSelector
        cssSelector.shouldBe("#foo[bar]")
    }

    test("can calculate combination of id and attribute selector from element") {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar",
                withId = "foobar"
        ).toCssSelector
        cssSelector.shouldBe("#foobar[foo='bar']")
    }

    test("can calculate combination of attribute and class selector from element") {
        val cssSelector = CssSelector(
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        cssSelector.shouldBe(".foobar[foo='bar']")
    }

    test("can calculate combination of attribute and attributeKey selector from element") {
        val cssSelector = CssSelector(
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        cssSelector.shouldBe("[foobar][foo='bar']")
    }

    test("can calculate combination of id, attributeKey and class selector from element") {
        val cssSelector = CssSelector(
                withId = "foobar",
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        cssSelector.shouldBe("#foobar.bar[foo]")
    }

    test("can calculate combination of id, attribute and attributeKey selector from element") {
        val cssSelector = CssSelector(
                withId = "fb",
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        cssSelector.shouldBe("#fb[foobar][foo='bar']")
    }

    test("can calculate combination of id, attribute and class selector from element") {
        val cssSelector = CssSelector(
                withId = "fb",
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        cssSelector.shouldBe("#fb.foobar[foo='bar']")
    }

    test("can calculate combination of attributeKey and class selector from element") {
        val cssSelector = CssSelector(
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        cssSelector.shouldBe(".bar[foo]")
    }

    test("can calculate complex css selector from element") {
        val cssSelector = CssSelector(
                rawCssSelector = "div span a",
                withClass = "bar",
                withId = "foo",
                withAttributeKey = "foobar",
                withAttribute = "fizz" to "buzz"
        ).toCssSelector
        cssSelector.shouldBe("div span a#foo.bar[foobar][fizz='buzz']")
    }

    test("parameter selector will be merged with element selector") {
        val cssSelector = CssSelector(
                withClass = "bar",
                rawCssSelector = "foo"
        ).toCssSelector
        cssSelector.shouldBe("foo.bar")
    }

    test("'toString()' implementation returns calculated css selector") {
        val cssSelector = "${CssSelector(
            rawCssSelector = "div span a",
            withClass = "bar",
            withId = "foo",
            withAttributeKey = "foobar",
            withAttribute = "fizz" to "buzz"
        )}"
        cssSelector.shouldBe("div span a#foo.bar[foobar][fizz='buzz']")
    }
})
