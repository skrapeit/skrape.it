
// TODO: need to fix jitpack release after module split

import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.selects.html5.a
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThanOrEqualTo


class BleedingEdgeReleaseTest {

    @Test
    internal fun `can use latest aggregator version from maven central and scrape page without JS`() {
        val projectsGithubStars = skrape(HttpFetcher) {
            request {
                url = "https://github.com/skrapeit/skrape.it"
            }
            extract {
                htmlDocument {
                    ".pagehead-actions" {
                        a {
                            withClass = "social-count"
                            findFirst { text }
                        }
                    }
                }
            }
        }

        expectThat(projectsGithubStars).isGreaterThanOrEqualTo("239")
    }

    @Test
    internal fun `can use latest aggregator version from maven central and scrape page with JS`() {
        val title = skrape(BrowserFetcher) {
            request {
                url = "https://docs.skrape.it"
            }
            extract {
                htmlDocument {
                    titleText
                }
            }
        }

        expectThat(title).isEqualTo("Introduction - skrape{it}")
    }

    @Test
    internal fun `can use latest aggregator version from maven central and scrape page async`() {
        val projectsGithubStars = runBlocking {
            skrape(AsyncFetcher) {
                request {
                    url = "https://github.com/skrapeit/skrape.it"
                }
                extract {
                    htmlDocument {
                        ".pagehead-actions" {
                            a {
                                withClass = "social-count"
                                findFirst { text }
                            }
                        }
                    }
                }
            }
        }

        expectThat(projectsGithubStars).isGreaterThanOrEqualTo("239")
    }
}
