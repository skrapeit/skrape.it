package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlin.reflect.full.createInstance

public class AsyncScraper<R>(public val client: AsyncFetcher<R>, public val preparedRequest: R) {
    public constructor(client: AsyncFetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    public fun request(init: R.() -> Unit): AsyncScraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    public suspend fun scrape(): Result =
        client.fetch(preparedRequest)
}

/**
 * Create a http-request config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public fun <R, T> skrape(client: AsyncFetcher<R>, init: AsyncScraper<R>.() -> T): T =
    AsyncScraper(client).init()

/**
 * Read and parse html from a skrape{it} result.
 */
@SkrapeItDsl
public suspend fun AsyncScraper<*>.expect(init: Result.() -> Unit) {
    extract(init)
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDsl
public suspend fun <T> AsyncScraper<*>.extract(extractor: Result.() -> T): T =
    scrape().extractor()

/**
 * Read and parse html from a skrape{it} result.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
public suspend inline fun <reified T : Any> AsyncScraper<*>.extractIt(crossinline extractor: Result.(T) -> Unit): T {
    val instance = T::class.createInstance()
    return extract { instance.also { extractor(it) } }
}
