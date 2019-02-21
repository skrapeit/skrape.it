package it.skrape

import it.skrape.core.*
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import kotlin.reflect.KClass

/**
 * make http-request with given parameters or defaults
 * @return Result
 */
@SkrapeItDslMarker
fun <T> skrape(init: Request.() -> T): T {
    val scraper = Scraper()
    return scraper.request.init()
}

/**
 * read and parse a html file from local file-system
 * @param path to file
 * @return Result
 */
@SkrapeItDslMarker
fun skrape(path: String, charset: String = "UTF-8", init: Doc.() -> Unit) {
    Reader(path, charset).read().init()
}

@SkrapeItDslMarker
fun Request.expect(init: Result.() -> Unit) {
    val result = Scraper(this).scrape()
    result.init()
}

@SkrapeItDslMarker
fun <T> Request.extract(extractor: Result.() -> T): T {
    val result = Scraper(this).scrape()
    return result.extractor()
}

@SkrapeItDslMarker
inline fun <reified T: Any> Request.extractIt(extractor: Result.(T) -> Unit): T {
    val instance = create(T::class)
    Scraper(this).scrape().apply { extractor(instance) }
    return instance
}

@SkrapeItDslMarker
fun Result.title(init: String.() -> Unit): String {
    val title = document.title()
    title.apply(init)
    return title
}

@SkrapeItDslMarker
fun Result.body(init: Element.() -> Unit): Element {
    val body = document.body()
    body.apply(init)
    return body
}

@SkrapeItDslMarker
fun Result.element(selector: String, init: Element.() -> Unit) {
    val element = document.selectFirst(selector) ?: throw ElementNotFoundException(selector)
    element.apply(init)
}

@SkrapeItDslMarker
fun Result.elements(selector: String, init: Elements.() -> Unit) {
    document.select(selector).apply(init)
}

@SkrapeItDslMarker
fun Result.headers(init: Map<String, String>.() -> Unit) : Map<String, String> {
    val headers = this.headers
    headers.apply(init)
    return headers
}

@SkrapeItDslMarker
fun Result.header(name: String, init: String?.() -> Unit) : String? {
    val headers = this.headers[name]
    headers.apply(init)
    return headers
}

inline fun <reified T: Any> create(clazz: KClass<T>): T {
    return clazz.constructors.first { it.parameters.isEmpty() }.call()
}

@DslMarker
annotation class SkrapeItDslMarker
