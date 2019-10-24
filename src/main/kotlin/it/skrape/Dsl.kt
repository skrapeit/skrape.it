package it.skrape

import it.skrape.core.*
import it.skrape.core.Reader
import java.io.File
import java.nio.charset.Charset
import kotlin.reflect.KClass


/**
 * Make http-request with given parameters or defaults.
 * @param mode defaults to HTTP and represents the scraping mode (either pure http request or rendering via browser)
 * @return Result
 */
@SkrapeItDslMarker
fun <T> skrape(init: Request.() -> T): T {
    return Scraper().request.init()
}

/**
 * Read and parse a html file from local file-system.
 * @param file
 * @param charset defaults to UTF-8
 */
@SkrapeItDslMarker
fun skrape(file: File, charset: Charset = Charsets.UTF_8, init: Doc.() -> Unit): Doc {
    return Reader(file, charset).read().also(init)
}

/**
 * Read and parse html from a String.
 * @param html represents a html snippet
 */
@SkrapeItDslMarker
fun skrape(html: String, init: Doc.() -> Unit): Doc {
    return Parser(html).parse().also(init)
}

/**
 * Read and parse a html file from local file-system.
 * @param file
 * @param charset defaults to UTF-8
 */
@SkrapeItDslMarker
fun expect(file: File, charset: Charset = Charsets.UTF_8, init: Doc.() -> Unit) {
    Reader(file, charset).read().init()
}

/**
 * Read and parse html from a String.
 * @param html represents a html snippet
 */
@SkrapeItDslMarker
fun expect(html: String, init: Doc.() -> Unit) {
    Parser(html).parse().init()
}

/**
 * Read and parse html from a skrape{it} result.
 */
@SkrapeItDslMarker
fun Request.expect(init: Result.() -> Unit) {
    Scraper(request = this).scrape().also(init)
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDslMarker
fun <T> Request.extract(extractor: Result.() -> T): T {
    val result = Scraper(request = this).scrape()
    return result.extractor()
}

/**
 * Read and parse html from a skrape{it} result.
 * @return T
 */
@SkrapeItDslMarker
inline fun <reified T: Any> Request.extractIt(extractor: Result.(T) -> Unit): T {
    val instance = create(T::class)
    Scraper(request = this).scrape().apply { extractor(instance) }
    return instance
}

/**
 * Creates an instance of generic with a no args constructor.
 * @return T
 */
inline fun <reified T: Any> create(clazz: KClass<T>): T {
    return clazz.constructors.first { it.parameters.isEmpty() }.call()
}



@DslMarker
annotation class SkrapeItDslMarker
