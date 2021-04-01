package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.createInstance

public class Scraper<R>(public val client: NonBlockingFetcher<R>, public val preparedRequest: R) {
    public constructor(client: NonBlockingFetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    public fun request(init: R.() -> Unit): Scraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    public suspend fun scrape(): Result =
        client.fetch(preparedRequest)
}

/**
 * convert a blocking Fetcher implementation into a non-blocking Fetcher
 */
private class BlockingToNonBlockingFetcherConverter<T>(
    val wrapped: BlockingFetcher<T>
): NonBlockingFetcher<T> {

    override suspend fun fetch(request: T): Result {
        return wrapped.fetch(request)
    }

    override val requestBuilder: T
        get() = wrapped.requestBuilder
}

/**
 * Create a scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public fun <R, T> skrape(client: BlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T = runBlocking {
    Scraper(BlockingToNonBlockingFetcherConverter(client)).init()
}

/**
 * Create a non-blocking scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public suspend fun <R, T> skrape(client: NonBlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T =
    Scraper(client).init()

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 */
@SkrapeItDsl
public suspend fun Scraper<*>.expect(init: Result.() -> Unit) {
    extract(init)
}

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
public suspend fun <T> Scraper<*>.extract(extractor: Result.() -> T): T =
    scrape().extractor()

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result as this and any given generic as it.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
public suspend inline fun <reified T : Any> Scraper<*>.extractIt(crossinline extractor: Result.(T) -> Unit): T =
    with(T::class) {
        extract { createInstance().also { extractor(it) } }
    }
