package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.matchers.toBePresent
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.selects.findAll
import it.skrape.selects.findFirst
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class SectioningSelectorsKtTest {

    @Test
    fun `can parse body-tag`() {
        val selector = aValidDocument().body {
            findFirst {
                toBePresent()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("body")
    }

    @Test
    fun `can parse div-tag`() {
        val selector = aValidDocument().div {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("div")
    }

    @Test
    fun `can parse section-tag`() {
        val selector = aValidDocument().section {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("section")
    }

    @Test
    fun `can parse nav-tag`() {
        val selector = aValidDocument().nav {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("nav")
    }

    @Test
    fun `can parse article-tag`() {
        val selector = aValidDocument().article {
            findFirst {
                expectThat(text()).isEqualTo("i'm an article")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("article")
    }

    @Test
    fun `can parse aside-tag`() {
        val selector = aValidDocument().aside {
            findFirst {
                expectThat(text()).isEqualTo("i'm an aside")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("aside")
    }

    @Test
    fun `can parse h1-tag`() {
        val selector = aValidDocument().h1 {
            findFirst {
                expectThat(text()).isEqualTo("i'm the headline")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h1")
    }

    @Test
    fun `can parse h2-tag`() {
        val selector = aValidDocument().h2 {
            findFirst {
                expectThat(text()).isEqualTo("i'm a h2")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h2")
    }

    @Test
    fun `can parse h3-tag`() {
        val selector = aValidDocument().h3 {
            findFirst {
                expectThat(text()).isEqualTo("i'm a h3")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h3")
    }

    @Test
    fun `can parse h4-tag`() {
        val selector = aValidDocument().h4 {
            findFirst {
                expectThat(text()).isEqualTo("i'm a h4")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h4")
    }

    @Test
    fun `can parse h5-tag`() {
        val selector = aValidDocument().h5 {
            findFirst {
                expectThat(text()).isEqualTo("i'm a h5")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h5")
    }

    @Test
    fun `can parse h6-tag`() {
        val selector = aValidDocument().h6 {
            findFirst {
                expectThat(text()).isEqualTo("i'm a h6")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("h6")
    }

    @Test
    fun `can parse header-tag`() {
        val selector = aValidDocument().header {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("header")
    }

    @Test
    fun `can parse footer-tag`() {
        val selector = aValidDocument().footer {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("footer")
    }

    @Test
    fun `can parse address-tag`() {
        val selector = aValidDocument().address {
            findFirst {
                expectThat(text()).isEqualTo("i'm an address")
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("address")
    }

    @Test
    fun `can parse main-tag`() {
        val selector = aValidDocument().main {
            findAll {
                toBePresentExactlyOnce()
            }
            rawCssSelector
        }

        expectThat(selector).isEqualTo("main")
    }
}