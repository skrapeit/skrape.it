package it.skrape.core

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class DomSelectorTest {

    @Test
    fun `can calculate selector from raw css selector param`() {
        val cssSelector = DomSelector(
                rawCssSelector = "#foo.bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate class selector from element`() {
        val cssSelector = DomSelector(
                withClass = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo(".foo")
    }

    @Test
    fun `can calculate list of classes selector from element`() {
        val cssSelector = DomSelector(
                withClass = "foo" and "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo(".foo.bar")
    }

    @Test
    fun `can calculate selector and be relaxed on miss-leading spaces`() {
        val cssSelector = DomSelector(
                withClass = "   foobar " and " foo " and " bar ",
                withAttribute = "   foooo " to "  bar   ",
                withAttributes = "fizz" to "buzz" and Pair("skrape", "it"),
                withAttributeKeys = listOf("key1", "key2")
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo(".foobar.foo.bar['key1']['key2'][foooo='bar'][fizz='buzz'][skrape='it']")
    }

    @Test
    fun `can calculate id selector from element`() {
        val cssSelector = DomSelector(
                withId = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foo")
    }

    @Test
    fun `can calculate attributeKey selector from element`() {
        val cssSelector = DomSelector(
                withAttributeKey = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("[foo]")
    }

    @Test
    fun `can calculate attribute selector from element`() {
        val cssSelector = DomSelector(
                withAttribute = "foo" to "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `can calculate combination of id and class selector from element`() {
        val cssSelector = DomSelector(
                withClass = "bar",
                withId = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate combination of id and attributeKey selector from element`() {
        val cssSelector = DomSelector(
                withAttributeKey = "bar",
                withId = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foo[bar]")
    }

    @Test
    fun `can calculate combination of id and attribute selector from element`() {
        val cssSelector = DomSelector(
                withAttribute = "foo" to "bar",
                withId = "foobar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and class selector from element`() {
        val cssSelector = DomSelector(
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo(".foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and attributeKey selector from element`() {
        val cssSelector = DomSelector(
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attributeKey and class selector from element`() {
        val cssSelector = DomSelector(
                withId = "foobar",
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foobar.bar[foo]")
    }

    @Test
    fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val cssSelector = DomSelector(
                withId = "fb",
                withAttributeKey = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#fb[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attribute and class selector from element`() {
        val cssSelector = DomSelector(
                withId = "fb",
                withClass = "foobar",
                withAttribute = "foo" to "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#fb.foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attributeKey and class selector from element`() {
        val cssSelector = DomSelector(
                withAttributeKey = "foo",
                withClass = "bar"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo(".bar[foo]")
    }

    @Test
    fun `can calculate complex css selector from element`() {
        val cssSelector = DomSelector(
                withClass = "bar",
                withId = "foo",
                withAttributeKey = "foobar",
                withAttribute = "fizz" to "buzz"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("#foo.bar[foobar][fizz='buzz']")
    }

    @Test
    fun `parameter selector will be merged with element selector`() {
        val cssSelector = DomSelector(
                withClass = "bar",
                rawCssSelector = "foo"
        ).toCssSelector()
        assertThat(cssSelector).isEqualTo("foo.bar")
    }

}
