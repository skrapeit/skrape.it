import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.selects.html5.span
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThanOrEqualTo

class BleedingEdgeReleaseTest {
    @Test
    internal fun `can use latest aggregator version from maven central and scrape page without JS`() {
        val projectsGithubStars = skrape(HttpFetcher) {
            request {
                url = "https://github.com/skrapeit/skrape.it"
            }
            response {
                htmlDocument {
                    ".pagehead-actions" {
                        0 {
                            val star = children.first {
                                it.text.contains("Star")
                            }
                            star.span {
                                withClass = "Counter"
                                0 {
                                    text
                                }
                            }
                        }
                    }
                }
            }
        }
        expectThat(projectsGithubStars).isGreaterThanOrEqualTo("449")
    }

    @Test
    internal fun `can use latest aggregator version from maven central and scrape page with JS`() {
        val title = skrape(BrowserFetcher) {
            request {
                url = "https://docs.skrape.it"
            }
            response {
                htmlDocument {
                    titleText
                }
            }
        }

        expectThat(title).contains("Introduction").contains("skrape{it}")
    }

    @Test
    internal fun `can use latest aggregator version from maven central and scrape page async`() {
        val projectsGithubStars = runBlocking {
            skrape(AsyncFetcher) {
                request {
                    url = "https://github.com/skrapeit/skrape.it"
                }
                response {
                    htmlDocument {
                        ".pagehead-actions" {
                            0 {
                                val star = children.first {
                                    it.text.contains("Star")
                                }
                                star.span {
                                    withClass = "Counter"
                                    0 {
                                        text
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        expectThat(projectsGithubStars).isGreaterThanOrEqualTo("239")
    }
}
