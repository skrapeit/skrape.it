package it.skrape

import it.skrape.core.fetcher.Request
import it.skrape.core.fetcher.Result
import it.skrape.core.Scraper
import kotlin.reflect.KClass


/**
 * Make http-request with given parameters or defaults.
 * @param mode defaults to HTTP and represents the scraping mode (either pure http request or rendering via browser)
 * @return Result
 */
@SkrapeItDsl
fun <T> skrape(init: Request.() -> T): T {
    return Scraper().request.init()
}

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
 * @return T
 */
@SkrapeItDsl
inline fun <reified T : Any> Request.extractIt(extractor: Result.(T) -> Unit): T {
    val instance = create(T::class)
    Scraper(request = this).scrape().apply { extractor(instance) }
    return instance
}

/**
 * Creates an instance of generic with a no args constructor.
 * @return T
 */
inline fun <reified T : Any> create(clazz: KClass<T>): T {
    return clazz.constructors.first { it.parameters.isEmpty() }.call()
}

@DslMarker
annotation class SkrapeItDsl

@DslMarker
annotation class SkrapeItElementPicker

@DslMarker
annotation class SkrapeItValuePicker

@DslMarker
annotation class SkrapeItResult

@DslMarker
annotation class SkrapeItAssertion
