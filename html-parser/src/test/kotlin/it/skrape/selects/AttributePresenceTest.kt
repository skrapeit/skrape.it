package it.skrape.selects

import it.skrape.core.htmlDocument
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AttributePresenceTest {
    @Test
    fun `HTML attribute presence includes boolean empty and whitespace values`() {
        val input = htmlDocument("""<input required disabled value="" data-note=" " aria-invalid="false">""")
            .findFirst("input")

        for (name in listOf("required", "disabled", "value", "data-note", "aria-invalid", "REQUIRED")) {
            assertTrue(input.hasAttribute(name), "Expected attribute $name to be present")
        }
        assertFalse(input.hasAttribute("missing"))
        assertFalse(htmlDocument("<input>").findFirst("input").hasAttribute("required"))
    }
}
