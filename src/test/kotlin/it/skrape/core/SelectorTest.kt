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
        val cssSelector = Selector(className = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foo")
    }

    @Test
    fun `can calculate list of classes selector from element`() {
        val cssSelector = Selector(classNames = listOf("foo", "bar"))
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foo.bar")
    }

    @Test
    fun `can calculate selector and be relaxed on miss-leading spaces`() {
        val cssSelector = Selector(
                classNames = listOf(" foo ", " bar "),
                className = "   foobar ",
                attribute = "   foooo " to "  bar   ",
                attributes = listOf("fizz" to "buzz", "skrape" to "it"),
                attributeKeys = listOf("key1", "key2")
        ).toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foobar.foo.bar[foooo='bar']")
    }

    @Test
    fun `can calculate id selector from element`() {
        val cssSelector = Selector(id = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo")
    }

    @Test
    fun `can calculate attributeKey selector from element`() {
        val cssSelector = Selector(attributeKey = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foo]")
    }

    @Test
    fun `can calculate attribute selector from element`() {
        val cssSelector = Selector(attribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `can calculate combination of id and class selector from element`() {
        val cssSelector = Selector(className = "bar", id = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate combination of id and attributeKey selector from element`() {
        val cssSelector = Selector(attributeKey = "bar", id = "foo")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo[bar]")
    }

    @Test
    fun `can calculate combination of id and attribute selector from element`() {
        val cssSelector = Selector(attribute = "foo" to "bar", id = "foobar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and class selector from element`() {
        val cssSelector = Selector(className = "foobar", attribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and attributeKey selector from element`() {
        val cssSelector = Selector(attributeKey = "foobar", attribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attributeKey and class selector from element`() {
        val cssSelector = Selector(id = "foobar", attributeKey = "foo", className = "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foobar.bar[foo]")
    }

    @Test
    fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val cssSelector = Selector(id = "fb", attributeKey = "foobar", attribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#fb[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attribute and class selector from element`() {
        val cssSelector = Selector(id = "fb", className = "foobar", attribute = "foo" to "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo("#fb.foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attributeKey and class selector from element`() {
        val cssSelector = Selector(attributeKey = "foo", className = "bar")
                .toCssSelector("")
        assertThat(cssSelector).isEqualTo(".bar[foo]")
    }

    @Test
    fun `can calculate complex css selector from element`() {
        val cssSelector = Selector(
                className = "bar",
                id = "foo",
                attributeKey = "foobar",
                attribute = "fizz" to "buzz"
        ).toCssSelector("")
        assertThat(cssSelector).isEqualTo("#foo.bar[foobar][fizz='buzz']")
    }

    @Test
    fun `parameter selector will win over element selector`() {
        val cssSelector = Selector(className = "bar")
                .toCssSelector("foo")
        assertThat(cssSelector).isEqualTo("foo")
    }

}
