package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.createInstance

public class Scraper<R>(public val client: AsyncFetcher<R>, public val preparedRequest: R) {
    public constructor(client: AsyncFetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    public fun request(init: R.() -> Unit): Scraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    public suspend fun scrape(): Result =
        client.fetch(preparedRequest)
}

/**
 * Wrap a blocking Fetcher implementation into a AsyncFetcher
 */
private class FetcherWrapper<T>(
    val wrapped: Fetcher<T>
): AsyncFetcher<T> {

    override suspend fun fetch(request: T): Result {
        return wrapped.fetch(request)
    }

    override val requestBuilder: T
        get() = wrapped.requestBuilder

}

/**
 * Create a http-request config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public fun <R, T> skrape(client: Fetcher<R>, init: suspend Scraper<R>.() -> T): T = runBlocking {
    Scraper(FetcherWrapper(client)).init()
}

@SkrapeItDsl
public suspend fun <R, T> skrape(client: AsyncFetcher<R>, init: suspend Scraper<R>.() -> T): T =
    Scraper(client).init()

/**
 * Read and parse html from a skrape{it} result.
 */
@SkrapeItDsl
public suspend fun Scraper<*>.expect(init: Result.() -> Unit) {
    extract(init)
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDsl
public suspend fun <T> Scraper<*>.extract(extractor: Result.() -> T): T =
    scrape().extractor()

/**
 * Read and parse html from a skrape{it} result.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
public suspend inline fun <reified T : Any> Scraper<*>.extractIt(crossinline extractor: Result.(T) -> Unit): T {
    val instance = T::class.createInstance()
    return extract { instance.also { extractor(it) } }
}
