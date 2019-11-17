package it.skrape

import it.skrape.core.Mode
import it.skrape.core.WireMockSetup
import it.skrape.core.and
import it.skrape.core.setupStub
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.matchers.toContain
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div
import org.junit.jupiter.api.Test

class ExperimentalDslTest : WireMockSetup() {

    @Test
    internal fun `can use latest features`() {
        wireMockServer.setupStub(path = "/example")

        val myText = skrape {
            url = "http://localhost:8080/example"

            extract {
                htmlDocument {
                    div {
                        withClass = "foo" and "bar" and "fizz" and "buzz"

                        findFirst {
                            text() toBe "div with class foo"
                        }

                        findAll {
                            toBePresentExactlyOnce()
                        }
                    }
                    customTag("a-custom-tag") {
                        findFirst {
                            text() toBe "i'm a custom html5 tag"
                            text()
                        }
                    }

                    "a-custom-tag" {
                        findFirst {
                            text() toBe "i'm a custom html5 tag"
                            text()
                        }
                    }

                    "div.foo.bar.fizz.buzz" {
                        findFirst {
                            text() toBe "div with class foo"
                        }
                    }

                    "div.foo" {

                        withClass = "bar" and "fizz" and "buzz"

                        findFirst {
                            text() toBe "div with class foo"
                        }
                    }
                }
            }
        }

        print(myText)
    }

    @Test
    internal fun `can scrape our docs page`() {
        skrape {
            url = "https://docs.skrape.it/docs/"
            mode = Mode.DOM

            extract {
                htmlDocument {
                    toString() toContain "A Story of Deserializing HTML / XML."
                }
            }
        }
    }
}