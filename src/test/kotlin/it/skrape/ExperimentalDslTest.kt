package it.skrape

import it.skrape.core.WireMockSetup
import it.skrape.core.setupStub
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.selects.findAll
import it.skrape.selects.findFirst
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div
import it.skrape.selects.htmlDocument
import org.junit.jupiter.api.Test

class ExperimentalDslTest : WireMockSetup() {

    @Test
    internal fun name() {
        wireMockServer.setupStub(path = "/example")

        skrape {
            url = "http://localhost:8080/example"
            expect {
                htmlDocument {
                    div {
                        withClass = "foo"

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
                        }
                    }
                }
            }
        }
    }
}