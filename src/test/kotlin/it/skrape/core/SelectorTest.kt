package it.skrape.core

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class SelectorTest {

    @Test
    fun `can calculate selector from parameter`() {
        val cssSelector = Selector()
                .toCssSelector("#foo.bar")
        assertThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate class selector from element`() {
        val cssSelector = Selector(withClass = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foo")
    }

    @Test
    fun `can calculate list of classes selector from element`() {
        val cssSelector = Selector(withClasses = listOf("foo", "bar"))
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foo.bar")
    }

    @Test
    fun `can calculate selector and be relaxed on miss-leading spaces`() {
        val cssSelector = Selector(
                withClasses = listOf(" foo ", " bar "),
                withClass = "   foobar ",
                withAttribute = "   foooo " to "  bar   ",
                withAttributes = listOf("fizz" to "buzz", "skrape" to "it"),
                withAttributeKeys = listOf("key1", "key2")
        ).toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foobar.foo.bar['key1']['key2'][foooo='bar'][fizz='buzz'][skrape='it']")
    }

    @Test
    fun `can calculate id selector from element`() {
        val cssSelector = Selector(withId = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo")
    }

    @Test
    fun `can calculate attributeKey selector from element`() {
        val cssSelector = Selector(withAttributeKey = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foo]")
    }

    @Test
    fun `can calculate attribute selector from element`() {
        val cssSelector = Selector(withAttribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `can calculate combination of id and class selector from element`() {
        val cssSelector = Selector(withClass = "bar", withId = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate combination of id and attributeKey selector from element`() {
        val cssSelector = Selector(withAttributeKey = "bar", withId = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo[bar]")
    }

    @Test
    fun `can calculate combination of id and attribute selector from element`() {
        val cssSelector = Selector(withAttribute = "foo" to "bar", withId = "foobar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and class selector from element`() {
        val cssSelector = Selector(withClass = "foobar", withAttribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and attributeKey selector from element`() {
        val cssSelector = Selector(withAttributeKey = "foobar", withAttribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attributeKey and class selector from element`() {
        val cssSelector = Selector(withId = "foobar", withAttributeKey = "foo", withClass = "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foobar.bar[foo]")
    }

    @Test
    fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val cssSelector = Selector(withId = "fb", withAttributeKey = "foobar", withAttribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#fb[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attribute and class selector from element`() {
        val cssSelector = Selector(withId = "fb", withClass = "foobar", withAttribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#fb.foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attributeKey and class selector from element`() {
        val cssSelector = Selector(withAttributeKey = "foo", withClass = "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".bar[foo]")
    }

    @Test
    fun `can calculate complex css selector from element`() {
        val cssSelector = Selector(
                withClass = "bar",
                withId = "foo",
                withAttributeKey = "foobar",
                withAttribute = "fizz" to "buzz"
        ).toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo.bar[foobar][fizz='buzz']")
    }

    @Test
    fun `parameter selector will be merged with element selector`() {
        val cssSelector = Selector(withClass = "bar")
                .toCssSelector("foo")
        assertThat(cssSelector).isEqualTo("foo.bar")
    }

}
