import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.matchers.toBe
import it.skrape.matchers.toBePresentExactlyOnce
import it.skrape.selects.and
import it.skrape.selects.html5.customTag
import it.skrape.selects.html5.div

class ExperimentalDslTest: FunSpec({

    extension(DockerExtension)
    extension(TestcontainerExtension)

    test("can use latest features").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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
    test("can use proxy to fetch sites") {
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
})