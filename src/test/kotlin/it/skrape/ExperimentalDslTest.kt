package it.skrape

import it.skrape.core.fetcher.Mode
import it.skrape.core.fetcher.UrlBuilder
import it.skrape.core.fetcher.basic
import it.skrape.core.htmlDocument
import it.skrape.matchers.*
import it.skrape.selects.and
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import it.skrape.selects.text
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.Proxy
import kotlin.reflect.full.createInstance

@Testcontainers
class ExperimentalDslTest : WireMockSetup() {

    @Container
    @JvmField
    val httpBin = KGenericContainer("kennethreitz/httpbin:latest")
            .apply { exposedPorts = listOf(80) }

    private fun httpBinUrl() = "http://${httpBin.containerIpAddress}:${httpBin.firstMappedPort}"

    @Test
    fun `can use latest features`() {
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
                            toBePresentExactlyOnce
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
    fun `can scrape our docs page`() {
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

    @ParameterizedTest(name = "can NOT scrape basic auth protected websites without credentials in {0}-mode")
    @EnumSource(Mode::class)
    fun `can NOT scrape basic auth protected websites without credentials`(fetcherMode: Mode) {

        skrape {
            mode = fetcherMode
            url = "${httpBinUrl()}/basic-auth/cr1z/secure"

            expect {
                status {
                    code toBe 401
                    message toBe "UNAUTHORIZED"
                }
            }
        }
    }

    @ParameterizedTest(name = "can scrape basic auth protected websites in {0}-mode")
    @EnumSource(Mode::class)
    fun `can scrape basic auth protected websites`(fetcherMode: Mode) {

        skrape {
            mode = fetcherMode
            url = "${httpBinUrl()}/basic-auth/cr1z/secure"

            authentication = basic {
                username = "cr1z"
                password = "secure"
            }

            expect {
                status {
                    code toBe 200
                }
                responseBody toContain """authenticated": true"""
                responseBody toContain """user": "cr1z"""
            }
        }
    }

    @Test
    fun `can invoke a raw nested css-selector`() {

        @Language("HTML") val myMarkUp = """
            <div class="CollapsiblePanelTab" tabindex="0">Today's Interest (1)</div>
                <div class="CollapsiblePanelContent">
                <table width="667px" class="tabularData">
                    <tr>
                        <td width="407px" height="21"><a href="link info i need in here">description </a></td>
                        <td width="130px">15:28</td>
                        <td width="130px">Western</td>
                    </tr> 
                </table>
            </div>
        """.trimIndent()

        htmlDocument(myMarkUp) {
            "table tr td a" {
                withAttributeKey = "href"
                findFirst {
                    println(attribute("href"))
                }
            }
        }
    }

    @Test
    fun `can nest selection via css selectors`() {

        @Language("HTML") val myMarkUp = """
            <div class="foo">
                <div class="bar">
                    <div>first nested div</div>
                </div>
            </div>
            <div class="foo">
                <div class="bar">
                    <div>other nested div</div>
                </div>
            </div>
            <div class="some-other"></div>
        """.trimIndent()

        htmlDocument(myMarkUp) {

            div {
                withClass = "foo"

                div {
                    withClass = "bar"

                    findAll {
                        text toBe "first nested div other nested div"
                    }

                    findFirst {
                        text toBe "first nested div"
                    }
                }
            }
        }
    }

    @Test
    fun `relaxed mode will not throw exception if element not exists`() {
        htmlDocument("""<span class="xxx"">hello</span>""") {
            relaxed = true
            span {
                withClass = "xxx"
                findAll { toBeNotEmpty }
                findFirst { text toBe "hello" }
            }
            span {
                withClass = "yyy"
                // in none relaxed mode it would throw an ElementNotFoundException when trying to find element without success
                findAll { toBeEmpty }
                findFirst { text toBe "" }
            }
            "some.crazy selectorThat[doesnt] exists" {
                // in none relaxed mode it would throw an ElementNotFoundException when trying to find element without success
                findAll { toBeEmpty }
            }
        }
    }

    @Disabled
    @Test
    fun `can  use proxy to fetch sites`() {
        skrape {
            url = "http://some.url"
            proxy = proxyBuilder {
                type = Proxy.Type.HTTP
                host = "http://some.proxy"
                port = 12345
            }
            extract {
                // do something with the result
            }

        }
    }
}

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)
