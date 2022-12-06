
import io.ktor.client.request.*
import io.ktor.http.*
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.selects.and
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.net.Proxy

private val wiremock = Testcontainer.wiremock

@Execution(ExecutionMode.SAME_THREAD)
@DisabledOnOs(OS.WINDOWS)
class ExperimentalDslTest {

    @Test
    fun `can use latest features`() = runTest {
        wiremock.setupStub(path = "/example")

        val myText = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response {
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

    /*
    TODO: Proxy currently not implemented
    @Disabled
    @Test
    fun `can use proxy to fetch sites`() = runTest {
        skrape(HttpFetcher) {
            request {
                url("http://some.url")

                proxy = proxyBuilder {
                    http(Url("http://some.proxy:12345"))
                }
            }
            response {
                // do something with the result
            }

        }
    }
    */
}