package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.exceptions.DivElementNotFoundException
import it.skrape.exceptions.MetaElementNotFoundException
import it.skrape.expect
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DocExtractorsTest {

    @Test
    internal fun `can read div from document`() {
        expect("<html><div>divs inner text</div></html>") {
            div {
                assertThat(text()).isEqualTo("divs inner text")
            }
        }
    }

    @Test
    internal fun `can read div with selector from document`() {
        expect("<html><div class=\"existent\">divs inner text</div></html>") {
            div(".existent") {
                assertThat(text()).isEqualTo("divs inner text")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if div could not be found via lambda`() {
        Assertions.assertThrows(DivElementNotFoundException::class.java) {
            expect("") {
                div(".nonExistent") {}
            }

        }
    }

    @Test
    internal fun `can read divs from document`() {
        expect("<html><div>first</div><div>second</div></html>") {
            divs {
                assertThat(size).isEqualTo(2)
                assertThat(get(0).text()).isEqualTo("first")
                assertThat(get(1).text()).isEqualTo("second")
            }
        }
    }

    @Test
    internal fun `can read divs with selector from document`() {
        expect("<html><div class=\"foo\">with class</div><div class=\"foo\">with class</div><div>without class</div></html>") {
            divs(".foo") {
                assertThat(size).isEqualTo(2)
                forEach {
                    assertThat(it.text()).isEqualTo("with class")
                }
            }
        }
    }

    @Test
    internal fun `will throw custom exception if divs could not be found via lambda`() {
        Assertions.assertThrows(DivElementNotFoundException::class.java) {
            expect("") {
                divs {}
            }

        }
    }

    @Test
    internal fun `can read meta from document`() {
        expect("<html><meta foo=\"bar\" /></html>") {
            meta {
                assertThat(attr("foo")).isEqualTo("bar")
            }
        }
    }

    @Test
    internal fun `can read meta with selector from document`() {
        expect("<html><meta foo=\"bar\" /></html>") {
            meta("[foo]") {
                assertThat(attr("foo")).isEqualTo("bar")
            }
        }
    }

    @Test
    internal fun `will throw custom exception if meta could not be found via lambda`() {
        Assertions.assertThrows(MetaElementNotFoundException::class.java) {
            expect("") {
                meta {}
            }

        }
    }

    @Test
    internal fun `can read metas from document`() {
        expect("<html><meta first=\"First\" /><meta second=\"Second\" /></html>") {
            metas {
                assertThat(size).isEqualTo(2)
                assertThat(get(0).attr("first")).isEqualTo("First")
                assertThat(get(1).attr("second")).isEqualTo("Second")
            }
        }
    }

    @Test
    internal fun `can read metas with selector from document`() {
        expect("<html><meta first=\"First\" /><meta second=\"Second\" /></html>") {
            metas("[first]") {
                assertThat(size).isEqualTo(1)
                forEach {
                    assertThat(it.attr("first")).isEqualTo("First")
                }
            }
        }
    }

    @Test
    internal fun `will throw custom exception if metas could not be found via lambda`() {
        Assertions.assertThrows(MetaElementNotFoundException::class.java) {
            expect("") {
                metas {}
            }

        }
    }
}
