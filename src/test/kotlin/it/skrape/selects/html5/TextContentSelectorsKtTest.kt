package it.skrape.selects.html5

import it.skrape.aValidDocument
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.matchers.toBePresentExactlyTwice
import it.skrape.matchers.toBePresentTimes
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class TextContentSelectorsKtTest {

    @Test
    fun `can parse blockquote-tag`() {
        val selector = aValidDocument().blockquote {
            findFirst {
                expectThat(text).isEqualTo("i'm a quote")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("blockquote")
    }

    @Test
    fun `can parse dd-tag`() {
        val selector = aValidDocument().dd {
            findAll {
                toBePresentTimes(2)
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dd")
    }

    @Test
    fun `can parse dir-tag`() {
        val selector = aValidDocument().dir {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dir")
    }

    @Test
    fun `can parse dl-tag`() {
        val selector = aValidDocument().dl {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dl")
    }

    @Test
    fun `can parse dt-tag`() {
        val selector = aValidDocument().dt {
            findAll {
                toBePresentTimes(2)
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("dt")
    }

    @Test
    fun `can parse figcaption-tag`() {
        val selector = aValidDocument().figcaption {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("figcaption")
    }

    @Test
    fun `can parse figure-tag`() {
        val selector = aValidDocument().figure {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("figure")
    }

    @Test
    fun `can parse hr-tag`() {
        val selector = aValidDocument().hr {
            findAll {
                toBePresentExactlyTwice
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("hr")
    }

    @Test
    fun `can parse li-tag`() {
        val selector = aValidDocument().li {
            findAll {
                toBePresentTimes(11)
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("li")
    }

    @Test
    fun `can parse ol-tag`() {
        val selector = aValidDocument().ol {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("ol")
    }

    @Test
    fun `can parse ul-tag`() {
        val selector = aValidDocument().ul {
            findAll {
                toBePresentExactlyOnce
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("ul")
    }

    @Test
    fun `can parse p-tag`() {
        val selector = aValidDocument().p {
            findLast {
                expectThat(text).isEqualTo("i'm the last paragraph")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("p")
    }

    @Test
    fun `can cascade p-tag`() {
        val selector = aValidDocument().p {
            withClass = "first"
            p {
                withClass = "second"
                p {
                    withClass = "third"
                    toCssSelector
                }
            }
        }

        expectThat(selector).isEqualTo("p.first p.second p.third")
    }

    @Test
    fun `can parse pre-tag`() {
        val selector = aValidDocument().pre {
            findFirst {
                expectThat(text).isEqualTo("i'm a pre")
            }
            toCssSelector
        }

        expectThat(selector).isEqualTo("pre")
    }
}