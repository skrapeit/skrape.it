package it.skrape.fetcher

import io.ktor.client.*
import it.skrape.SkrapeItDsl

class Scraper<R>(val fetcher: NonBlockingFetcher<R>, val preparedRequest: R) {
    constructor(client: NonBlockingFetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    fun request(init: R.() -> Unit): Scraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    suspend fun scrape(): Result =
        fetcher.fetch(preparedRequest)
}

/**
 * Decorator that Implements a non-blocking fetcher with a given blocking Fetcher.
 * Hint: The thereby created NonBlockingFetcher will still behave like a BlockingFetcher.
 * It will only be converted to reduce library internal code duplication by handling everything as a coroutine internally.
 */
internal class FetcherConverter<T>(
    val blockingFetcher: BlockingFetcher<T>
) : NonBlockingFetcher<T> {

    override suspend fun fetch(request: T): Result {
        return blockingFetcher.fetch(request)
    }

    override val requestBuilder: T
        get() = blockingFetcher.requestBuilder
}

/**
 * Create a scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
@Deprecated(message = "Fetchers are deprecated", replaceWith = ReplaceWith("skrape(init)"))
expect fun <R, T> skrape(fetcher: BlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T

/**
 * Create a non-blocking scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
@Deprecated(message = "Fetchers are deprecated", replaceWith = ReplaceWith("skrape(init)"))
suspend fun <R, T> skrape(fetcher: NonBlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T =
    Scraper(fetcher).init()

/**
 * Create a non-blocking scraper config with given parameters or defaults.
 * @return Result
 */
suspend fun <T> skrape(init: suspend KtorScraper.() -> T): T =
    KtorScraper().init()

suspend fun <T> skrape(
    config: HttpClientConfig<*>.() -> Unit = KtorScraper.EMPTY_CONFIG,
    init: suspend KtorScraper.() -> T
): T =
    KtorScraper(config).init()

suspend fun <T> KtorScraper.response(result: Result.() -> T): T =
    scrape().result()

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
suspend fun Scraper<*>.expect(result: Result.() -> Unit) {
    response(result)
}

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
suspend fun <T> Scraper<*>.extract(result: Result.() -> T): T =
    response(result)

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
suspend fun <T> Scraper<*>.response(result: Result.() -> T): T =
    scrape().result()
