package it.skrape.fetcher

import io.ktor.client.*
import it.skrape.SkrapeItDsl

/**
 * Create a non-blocking scraper config with given parameters or defaults.
 * @return Result
 */
suspend fun <T> skrape(
    config: HttpClientConfig<*>.() -> Unit = Scraper.EMPTY_CONFIG,
    init: suspend Scraper.() -> T
): T =
    Scraper(config = config).init()

suspend fun <T> Scraper.response(scrapingResult: suspend ScrapingResult.() -> T): T =
    scrape().scrapingResult()

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
suspend fun Scraper.expect(scrapingResult: suspend ScrapingResult.() -> Unit) {
    response(scrapingResult)
}

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
suspend fun <T> Scraper.extract(scrapingResult: suspend ScrapingResult.() -> T): T =
    response(scrapingResult)
