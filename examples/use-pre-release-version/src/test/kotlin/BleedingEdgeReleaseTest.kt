
// TODO: need to fix jitpack release after module split

//import it.skrape.core.htmlDocument
//import it.skrape.fetcher.HttpFetcher
//import it.skrape.fetcher.extract
//import it.skrape.fetcher.skrape
//import it.skrape.selects.html5.a
//import org.junit.jupiter.api.Test
//import strikt.api.expectThat
//import strikt.assertions.isGreaterThanOrEqualTo
//
//
//class BleedingEdgeReleaseTest {
//
//    @Test
//    internal fun `can use latest aggregator version from jitpack`() {
//        val projectsGithubStars = skrape(HttpFetcher) {
//            request {
//                url = "https://github.com/skrapeit/skrape.it"
//            }
//            extract {
//                htmlDocument {
//                    ".pagehead-actions" {
//                        a {
//                            withClass = "social-count"
//                            findFirst { text }
//                        }
//                    }
//                }
//            }
//        }
//
//        expectThat(projectsGithubStars).isGreaterThanOrEqualTo("239")
//    }
//}
