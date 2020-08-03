package it.skrape.selects

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class CssSelectorTest {

    @Test
    fun `can calculate selector from raw css selector param`() {
        val cssSelector = CssSelector(
                rawCssSelector = "#foo.bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate selector from raw css selector param with spaces`() {
        val cssSelector = CssSelector(
                rawCssSelector = "div #foo.bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("div #foo.bar")
    }

    @Test
    fun `can calculate class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo(".foo")
    }

    @Test
    fun `can calculate list of classes selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foo" and "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo(".foo.bar")
    }

    @Test
    fun `can calculate selector and be relaxed on miss-leading spaces`() {
        val cssSelector = CssSelector(
                withClass = "   foobar " and " foo " and " bar ",
                withAttribute = "   foooo " to "  bar   ",
                withAttributes = "fizz" to "buzz" and Pair("skrape", "it"),
                withAttributeKeys = listOf("key1", "key2")
        ).toCssSelector
        expectThat(cssSelector).isEqualTo(".foobar.foo.bar['key1']['key2'][foooo='bar'][fizz='buzz'][skrape='it']")
    }

    @Test
    fun `can calculate id selector from element`() {
        val cssSelector = CssSelector(
                withId = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foo")
    }

    @Test
    fun `can calculate attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("[foo]")
    }

    @Test
    fun `can calculate attribute selector from element`() {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `can calculate combination of id and class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "bar",
                withId = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate combination of id and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "bar",
                withId = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foo[bar]")
    }

    @Test
    fun `can calculate combination of id and attribute selector from element`() {
        val cssSelector = CssSelector(
                withAttribute = "foo" to "bar",
                withId = "foobar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and class selector from element`() {
        val cssSelector = CssSelector(
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo(".foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attributeKey and class selector from element`() {
        val cssSelector = CssSelector(
                withId = "foobar",
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#foobar.bar[foo]")
    }

    @Test
    fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val cssSelector = CssSelector(
                withId = "fb",
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#fb[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attribute and class selector from element`() {
        val cssSelector = CssSelector(
                withId = "fb",
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("#fb.foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attributeKey and class selector from element`() {
        val cssSelector = CssSelector(
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo(".bar[foo]")
    }

    @Test
    fun `can calculate complex css selector from element`() {
        val cssSelector = CssSelector(
                rawCssSelector = "div span a",
                withClass = "bar",
                withId = "foo",
                withAttributeKey = "foobar",
                withAttribute = "fizz" to "buzz"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("div span a#foo.bar[foobar][fizz='buzz']")
    }

    @Test
    fun `parameter selector will be merged with element selector`() {
        val cssSelector = CssSelector(
                withClass = "bar",
                rawCssSelector = "foo"
        ).toCssSelector
        expectThat(cssSelector).isEqualTo("foo.bar")
    }
}
