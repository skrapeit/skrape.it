package it.skrape.selects

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.aValidResult
import it.skrape.core.Request
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.exceptions.*
import it.skrape.expect
import it.skrape.extract
import it.skrape.matchers.toBe
import it.skrape.matchers.toContain
import it.skrape.skrape
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ResultExtractorsTest : WireMockSetup() {

    @Test
    internal fun `will throw custom exception if element could not be found via element function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            Request().expect {
                element(".nonExistent")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if element could not be found via el function`() {

        Assertions.assertThrows(ElementNotFoundException::class.java) {
            Request().expect {
                el(".nonExistent")
            }
        }
    }

    @Test
    internal fun `can pick elements via select functions`() {
        wireMockServer.setupStub()

        val expectedValue = "i'm a paragraph"

        skrape {
            extract {
                assertAll {
                    assertThat(el("p").text()).isEqualTo(expectedValue)
                    assertThat(element("p").text()).isEqualTo(expectedValue)
                    assertThat(elements("p").first().text()).isEqualTo(expectedValue)
                    assertThat(`$`("p").first().text()).isEqualTo(expectedValue)
                }

            }
        }
    }

    @Test
    internal fun `can pick certain header select functions`() {
        wireMockServer.setupStub()

        skrape {
            expect {
                header("Content-Type") toBe "text/html; charset=UTF-8"
                header("Content-Type") toContain "html"
                header("notExisting") toBe null
            }
        }
    }

    @Test
    internal fun `can read div from document`() {
        val result = aValidResult("<html><div>divs inner text</div></html>")
        result.div {
            assertThat(text()).isEqualTo("divs inner text")
        }
    }

    @Test
    internal fun `can read div with selector from document`() {
        val result = aValidResult("<html><div class=\"existent\">divs inner text</div></html>")
        result.div(".existent") {
            assertThat(text()).isEqualTo("divs inner text")
        }
    }

    @Test
    internal fun `will throw custom exception if div could not be found via lambda`() {
        Assertions.assertThrows(DivElementNotFoundException::class.java) {
            aValidResult().div(".nonExistent") {}
        }
    }

    @Test
    internal fun `can read divs from document`() {
        val result = aValidResult("<html><div>first</div><div>second</div></html>")
        result.divs {
            assertThat(size).isEqualTo(2)
            assertThat(get(0).text()).isEqualTo("first")
            assertThat(get(1).text()).isEqualTo("second")
        }
    }

    @Test
    internal fun `can read divs with selector from document`() {
        val result = aValidResult("<html><div class=\"foo\">with class</div><div class=\"foo\">with class</div><div>without class</div></html>")
        result.divs(".foo") {
            assertThat(size).isEqualTo(2)
            forEach {
                assertThat(it.text()).isEqualTo("with class")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if divs could not be found via lambda`() {
        Assertions.assertThrows(DivElementNotFoundException::class.java) {
            aValidResult().divs {}
        }
    }

    @Test
    internal fun `can read span from document`() {
        val result = aValidResult("<html><span>span inner text</span></html>")
        result.span {
            assertThat(text()).isEqualTo("span inner text")
        }
    }

    @Test
    internal fun `can read span with selector from document`() {
        val result = aValidResult("<html><span class=\"existent\">span inner text</span></html>")
        result.span(".existent") {
            assertThat(text()).isEqualTo("span inner text")
        }
    }

    @Test
    internal fun `will throw custom exception if span could not be found via lambda`() {
        Assertions.assertThrows(SpanElementNotFoundException::class.java) {
            aValidResult().span(".nonExistent") {}
        }
    }

    @Test
    internal fun `can read spans from document`() {
        val result = aValidResult("<html><span>first</span><span>second</span></html>")
        result.spans {
            assertThat(size).isEqualTo(2)
            assertThat(get(0).text()).isEqualTo("first")
            assertThat(get(1).text()).isEqualTo("second")
        }
    }

    @Test
    internal fun `can read spans with selector from document`() {
        val result = aValidResult("<html><span class=\"foo\">with class</span><span class=\"foo\">with class</span><span>without class</span></html>")
        result.spans(".foo") {
            assertThat(size).isEqualTo(2)
            forEach {
                assertThat(it.text()).isEqualTo("with class")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if spans could not be found via lambda`() {
        Assertions.assertThrows(SpanElementNotFoundException::class.java) {
            aValidResult().spans {}
        }
    }

    @Test
    internal fun `can read strong from document`() {
        val result = aValidResult("<html><strong>strong inner text</strong></html>")
        result.strong {
            assertThat(text()).isEqualTo("strong inner text")
        }
    }

    @Test
    internal fun `can read strong with selector from document`() {
        val result = aValidResult("<html><strong class=\"existent\">strong inner text</strong></html>")
        result.strong(".existent") {
            assertThat(text()).isEqualTo("strong inner text")
        }
    }

    @Test
    internal fun `will throw custom exception if strong could not be found via lambda`() {
        Assertions.assertThrows(StrongElementNotFoundException::class.java) {
            aValidResult().strong(".nonExistent") {}
        }
    }

    @Test
    internal fun `can read strongs from document`() {
        val result = aValidResult("<html><strong>first</strong><strong>second</strong></html>")
        result.strongs {
            assertThat(size).isEqualTo(2)
            assertThat(get(0).text()).isEqualTo("first")
            assertThat(get(1).text()).isEqualTo("second")
        }
    }

    @Test
    internal fun `can read strongs with selector from document`() {
        val result = aValidResult("<html><strong class=\"foo\">with class</strong><strong class=\"foo\">with class</strong><strong>without class</strong></html>")
        result.strongs(".foo") {
            assertThat(size).isEqualTo(2)
            forEach {
                assertThat(it.text()).isEqualTo("with class")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if strongs could not be found via lambda`() {
        Assertions.assertThrows(StrongElementNotFoundException::class.java) {
            aValidResult().strongs {}
        }
    }

    @Test
    internal fun `can read meta from document`() {
        val result = aValidResult("<html><meta first=\"First\" /></html>")
        result.meta {
            assertThat(attr("first")).isEqualTo("First")
        }
    }

    @Test
    internal fun `can read meta with selector from document`() {
        val result = aValidResult("<html><meta first=\"First\" /></html>")
        result.meta("[first]") {
            assertThat(attr("first")).isEqualTo("First")
        }
    }

    @Test
    internal fun `will throw custom exception if meta could not be found via lambda`() {
        Assertions.assertThrows(MetaElementNotFoundException::class.java) {
            aValidResult().meta(".nonExistent") {}
        }
    }

    @Test
    internal fun `can read metas from document`() {
        val result = aValidResult("<html><meta first=\"First\" /><meta second=\"Second\" /></html>")
        result.metas {
            assertThat(size).isEqualTo(2)
            assertThat(get(0).attr("first")).isEqualTo("First")
            assertThat(get(1).attr("second")).isEqualTo("Second")
        }
    }

    @Test
    internal fun `can read metas with selector from document`() {
        val result = aValidResult("<html><meta first=\"First\" /><meta second=\"Second\" /></html>")
        result.metas("[first]") {
            assertThat(size).isEqualTo(1)
            forEach {
                assertThat(it.attr("first")).isEqualTo("First")
            }
        }

    }

    @Test
    internal fun `will throw custom exception if metas could not be found via lambda`() {
        Assertions.assertThrows(MetaElementNotFoundException::class.java) {
            aValidResult().metas {}
        }
    }
}
