package it.skrape

import it.skrape.core.*
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

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
fun skrapeFile(path: String, init: Doc.() -> Unit) {
    Reader(path).read().init()
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
fun <T> Request.extractIt(extractor: (result: Result) -> T): T {
    val result = Scraper(this).scrape()
    return extractor(result)
}

@SkrapeItDslMarker
fun Result.title(init: String.() -> Unit): String {
    val title = document.title()
    title.apply(init)
    return title
}

@SkrapeItDslMarker
fun Result.element(selector: String, init: Element.() -> Unit) {
    document.selectFirst(selector).apply(init)
}

@SkrapeItDslMarker
fun Result.elements(selector: String, init: Elements.() -> Unit) {
    document.select(selector).apply(init)
}

@DslMarker
annotation class SkrapeItDslMarker
