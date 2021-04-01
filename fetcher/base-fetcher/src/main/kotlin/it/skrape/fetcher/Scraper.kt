package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.createInstance

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
private class FetcherConverter<T>(
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
public fun <R, T> skrape(fetcher: BlockingFetcher<R>, init: suspend Scraper<R>.() -> T): T = runBlocking {
    Scraper(FetcherConverter(fetcher)).init()
}

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
public suspend fun Scraper<*>.expect(result: Result.() -> Unit) {
    extract(result)
}

/**
 * Blocking implementation of 'expect' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
public fun Scraper<*>.expectBlocking(result: Result.() -> Unit) {
    runBlocking { extract(result) }
}

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result.
 * @return T
 */
@SkrapeItDsl
public suspend fun <T> Scraper<*>.extract(result: Result.() -> T): T =
    scrape().result()

/**
 * Blocking implementation of 'extract' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
public fun <T> Scraper<*>.extractBlocking(result: Result.() -> T): T =
    runBlocking { extract(result) }

/**
 * Execute http call with a given Fetcher implementation and invoke the fetching result as this and any given generic as it.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
public suspend inline fun <reified T : Any> Scraper<*>.extractIt(crossinline result: Result.(T) -> Unit): T =
    with(T::class) {
        extract { createInstance().also { result(it) } }
    }

/**
 * Blocking implementation of 'extractIt' as convenience function to call it outside of an coroutine.
 * @return T
 */
@SkrapeItDsl
public inline fun <reified T : Any> Scraper<*>.extractItBlocking(crossinline result: Result.(T) -> Unit): T =
    runBlocking { extractIt(result) }
