package it.skrape

import it.skrape.core.fetcher.Request
import it.skrape.core.fetcher.Result
import it.skrape.core.Scraper
import kotlin.reflect.full.createInstance


/**
 * Create a http-request config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
fun <T> skrape(init: Request.() -> T): T = Scraper().request.init()

/**
 * Read and parse html from a skrape{it} result.
 */
@SkrapeItDsl
fun Request.expect(init: Result.() -> Unit) {
    Scraper(request = this).scrape().also(init)
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDsl
fun <T> Request.extract(extractor: Result.() -> T): T {
    val result = Scraper(request = this).scrape()
    return result.extractor()
}

/**
 * Read and parse html from a skrape{it} result.
 * Attention: extract to class is only supported for classes where all parameters have default values
 * @return T
 */
@SkrapeItDsl
inline fun <reified T : Any> Request.extractIt(extractor: Result.(T) -> Unit): T {
    val instance = T::class.createInstance()
    Scraper(request = this).scrape().apply { extractor(instance) }
    return instance
}

@DslMarker
annotation class SkrapeItDsl
