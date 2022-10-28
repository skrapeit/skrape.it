package it.skrape.fetcher

import it.skrape.SkrapeItDsl

public class Scraper<R>(public val fetcher: NonBlockingFetcher<R>, public val preparedRequest: R) {
    public constructor(client: NonBlockingFetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    public fun request(init: R.() -> Unit): Scraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    public suspend fun scrape(): Result =
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
public expect fun <R, T> skrape(fetcher: BlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T

/**
 * Create a non-blocking scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public suspend fun <R, T> skrape(fetcher: NonBlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T =
    Scraper(fetcher).init()

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
public suspend fun Scraper<*>.expect(result: Result.() -> Unit) {
    response(result)
}

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
@Deprecated(message = "Please use 'response' instead", replaceWith = ReplaceWith("response(result)"))
public suspend fun <T> Scraper<*>.extract(result: Result.() -> T): T =
    response(result)

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
public suspend fun <T> Scraper<*>.response(result: Result.() -> T): T =
    scrape().result()
