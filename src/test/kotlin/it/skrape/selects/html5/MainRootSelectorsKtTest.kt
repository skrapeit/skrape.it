package it.skrape.selects.html5

import it.skrape.aValidDocument
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@Execution(CONCURRENT)
internal class MainRootSelectorsKtTest {

    @Test
    internal fun `can pick html-tag`() {
        val selector = aValidDocument().html("") {
            findFirst {
                expectThat(attribute("lang")).isEqualTo("en")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("html")
    }
}
