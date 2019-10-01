package it.skrape.selects

import assertk.assertThat
import assertk.assertions.isEqualTo
import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.extract
import it.skrape.skrape
import org.junit.jupiter.api.Test

internal class HtmlExtractorsTest : WireMockSetup() {

    @Test
    internal fun `can pick html via html function`() {
        wireMockServer.setupStub()

        skrape {
            extract {
                html {
                    assertThat(attr("lang")).isEqualTo("en")
                }
            }
        }
    }

    @Test
    internal fun `can pick head via head function`() {
        wireMockServer.setupStub()

        val expectedValud = "<title>i'm the title</title>"

        skrape {
            extract {
                head {
                    assertThat(html()).isEqualTo(expectedValud)
                }
            }
        }
    }

    @Test
    internal fun `can pick paragraphs via p function`() {
        wireMockServer.setupStub()

        val expectedValue = "i'm a paragraph"

        skrape {
            extract {
                p {
                    assertThat(text()).isEqualTo(expectedValue)
                }
            }
        }
    }

	@Test
	internal fun `can pick list items via li function`() {
		wireMockServer.setupStub(fileName = "li_tag_example.html")

		val expectedValue = "First Item"

		skrape {
			extract {
				li {
					assertThat(html()).isEqualTo(expectedValue)
				}
			}
		}
	}

	@Test
	internal fun `can pick lists via ol function`() {
		wireMockServer.setupStub(fileName = "li_tag_example.html")

		val expectedValue = "<li>First Item</li>"

		skrape {
			extract {
				ol {
					assertThat(html()).isEqualTo(expectedValue)
				}
			}
		}
	}

    @Test
    internal fun `can pick nav tag via nav function`() {
        wireMockServer.setupStub(fileName = "li_tag_example.html")

        val expectedValue = "<ol> \n" +
                " <li>First Item</li> \n" +
                "</ol>"

        skrape {
            extract {
                nav {
                    assertThat(html()).isEqualTo(expectedValue)
                }
            }
        }
    }
}
