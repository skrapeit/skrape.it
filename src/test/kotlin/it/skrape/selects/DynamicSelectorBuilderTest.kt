package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.ScrapebleElements
import org.junit.jupiter.api.Test

internal class DynamicSelectorBuilderTest {

    @Test
    fun `will calculate selector from parameter`() {
        val selector = calculatedSelector("#foo.bar", ScrapebleElements())
        assertThat(selector).isEqualTo("#foo.bar")
    }

    @Test
    fun `will calculate class selector from element`() {
        val elementWithSelectors = ScrapebleElements(className = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo(".foo")
    }

    @Test
    fun `will calculate id selector from element`() {
        val elementWithSelectors = ScrapebleElements(id = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo")
    }

    @Test
    fun `will calculate attributeKey selector from element`() {
        val elementWithSelectors = ScrapebleElements(attributeKey = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("[foo]")
    }

    @Test
    fun `will calculate attribute selector from element`() {
        val elementWithSelectors = ScrapebleElements(attribute = "foo" to "bar")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("[foo='bar']")
    }

    @Test
    fun `will calculate combination of id and class selector from element`() {
        val elementWithSelectors = ScrapebleElements(className = "bar", id = "foo")
        val selector = calculatedSelector("", elementWithSelectors)
        assertThat(selector).isEqualTo("#foo.bar")
    }

    @Test
    fun `will calculate complex css selector from element`() {
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
