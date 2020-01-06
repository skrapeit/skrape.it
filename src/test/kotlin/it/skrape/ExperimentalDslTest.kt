package it.skrape

import it.skrape.core.fetcher.Mode
import it.skrape.core.fetcher.basic
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.matchers.toContain
import it.skrape.selects.and
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div
import org.junit.jupiter.api.Disabled
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
                            text toBe "div with class foo"
                        }

                        findAll {
                            toBePresentExactlyOnce()
                        }
                    }
                    customTag("a-custom-tag") {
                        findFirst {
                            text toBe "i'm a custom html5 tag"
                            text
                        }
                    }

                    "a-custom-tag" {
                        findFirst {
                            text toBe "i'm a custom html5 tag"
                        }
                    }

                    "div.foo.bar.fizz.buzz" {
                        findFirst {
                            text toBe "div with class foo"
                        }
                    }

                    "div.foo" {

                        withClass = "bar" and "fizz" and "buzz"

                        findFirst {
                            text toBe "div with class foo"
                            attribute("class")
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

    @Test
    internal fun `can NOT scrape basic auth protected websites without credentials`() {
        wireMockServer.setupBasicAuthStub(
                username = "cr1z",
                password = "secure"
        )

        skrape {
            path = "/basic-auth"

            expect {
                statusCode toBe 403
            }
        }
    }

    @Disabled("find out how to configure basic auth for wiremock properly")
    @Test
    internal fun `can scrape basic auth protected websites`() {
        wireMockServer.setupBasicAuthStub(
                username = "cr1z",
                password = "secure"
        )

        skrape {
            path = "/basic-auth"

            authentication = basic {
                username = "cr1z"
                password = "secure"
            }

            expect {
                statusCode toBe 200
            }
        }
    }
}