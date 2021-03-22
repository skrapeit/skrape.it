
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.htmlDocument
import it.skrape.extract
import it.skrape.selects.eachHref
import it.skrape.selects.html5.a
import it.skrape.skrape
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

public fun main() {
    val hits = KotlinWeekly().search()
    hits.forEach {
        println(it)
    }
}

public class KotlinWeekly {

    public fun search(): List<String> {
        var allLinks = listOf<String>()
        runBlocking {
            @Suppress("MagicNumber")
            val deferred = (220..223).map { issueNumber ->
                async {
                    scrape(issueNumber)
                }
            }


            allLinks = deferred.awaitAll().flatten()
        }

        return allLinks
    }

    private fun scrape(issueNumber: Int) =
        skrape(HttpFetcher) {
            request {
                url = "https://mailchi.mp/kotlinweekly/kotlin-weekly-$issueNumber"
            }
            extract {
                htmlDocument {
                    a {
                        findAll {
                            eachHref
                        }
                    }
                }
            }
        }
}
