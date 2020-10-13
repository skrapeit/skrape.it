package it.skrape

import it.skrape.core.Scraper
import it.skrape.core.fetcher.Fetcher
import it.skrape.core.fetcher.Result
import kotlin.reflect.full.createInstance


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
public fun Scraper<*>.expect(init: Result.() -> Unit) { extract(init) }

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

@DslMarker
public annotation class SkrapeItDsl
