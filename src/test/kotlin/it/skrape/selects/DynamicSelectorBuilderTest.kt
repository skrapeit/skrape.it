package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.ScrapebleElements
import org.junit.jupiter.api.Test

internal class DynamicSelectorBuilderTest {

    @Test
    fun `can calculate selector from parameter`() {
        val selector = calculatedSelector("#foo.bar", ScrapebleElements())
        assertThat(selector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate class selector from element`() {
        val elementWithSelectors = ScrapebleElements(className = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo(".foo")
    }

    @Test
    fun `can calculate id selector from element`() {
        val elementWithSelectors = ScrapebleElements(id = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo")
    }

    @Test
    fun `can calculate attributeKey selector from element`() {
        val elementWithSelectors = ScrapebleElements(attributeKey = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("[foo]")
    }

    @Test
    fun `can calculate attribute selector from element`() {
        val elementWithSelectors = ScrapebleElements(attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `can calculate combination of id and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(className = "bar", id = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo.bar")
    }

    @Test
    fun `can calculate combination of id and attributeKey selector from element`() {
        val elementWithSelectors = ScrapebleElements(attributeKey = "bar", id = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo[bar]")
    }

    @Test
    fun `can calculate combination of id and attribute selector from element`() {
        val elementWithSelectors = ScrapebleElements(attribute = "foo" to "bar", id = "foobar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(className = "foobar", attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo(".foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attribute and attributeKey selector from element`() {
        val elementWithSelectors = ScrapebleElements(attributeKey = "foobar", attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attributeKey and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(id = "foobar", attributeKey = "foo", className = "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foobar.bar[foo]")
    }

    @Test
    fun `can calculate combination of id, attribute and attributeKey selector from element`() {
        val elementWithSelectors = ScrapebleElements(id = "fb", attributeKey = "foobar", attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#fb[foobar][foo='bar']")
    }

    @Test
    fun `can calculate combination of id, attribute and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(id = "fb", className= "foobar", attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#fb.foobar[foo='bar']")
    }

    @Test
    fun `can calculate combination of attributeKey and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(attributeKey = "foo", className = "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo(".bar[foo]")
    }

    @Test
    fun `can calculate complex css selector from element`() {
        val elementWithSelectors = ScrapebleElements(
                className = "bar",
                id = "foo",
                attributeKey = "foobar",
                attribute = "fizz" to "buzz"
        )
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo.bar[foobar][fizz='buzz']")
    }

    @Test
    fun `parameter selector will win over element selector`() {
        val elementWithSelectors = ScrapebleElements(className = "bar")
        val selector = calculatedSelector("foo", elementWithSelectors)
        assertThat(selector).isEqualTo("foo")
    }

}
