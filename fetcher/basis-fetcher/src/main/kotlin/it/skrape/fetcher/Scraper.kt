package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlin.reflect.full.createInstance

public class Scraper<R>(public val client: Fetcher<R>, public val preparedRequest: R) {
    public constructor(client: Fetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    public fun request(init: R.() -> Unit): Scraper<R> {
        this.preparedRequest.run(init)
        return this
    }

    public fun scrape(): Result =
            client.fetch(preparedRequest)
}

/**
 * Create a http-request config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
public fun <R, T> skrape(client: Fetcher<R>, init: Scraper<R>.() -> T): T =
    Scraper(client).init()

/**
 * Read and parse html from a skrape{it} result.
 */
@SkrapeItDsl
public fun Scraper<*>.expect(init: Result.() -> Unit) {
    extract(init)
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDsl
public fun <T> Scraper<*>.extract(extractor: Result.() -> T): T =
    scrape().extractor()

/**
 * Read and parse html from a skrape{it} result.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
public inline fun <reified T : Any> Scraper<*>.extractIt(crossinline extractor: Result.(T) -> Unit): T {
    val instance = T::class.createInstance()
    return extract { instance.also { extractor(it) } }
}
