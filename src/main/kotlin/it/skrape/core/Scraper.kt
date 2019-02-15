package it.skrape.core

import org.jsoup.nodes.Document

class Scraper(val request: Request = Request()) {

    fun scrape(): Result {
        val response = Fetcher(request).fetch()
        return Result(response = response, request = request)
    }
}

/**
 * make http-request with given parameters or defaults
 * @param init as callback on Request-Object
 * @return Result
 */
@SkrapeItDslMarker
fun <T> skrape(init: Request.() -> T): T {
    val scraper = Scraper()
    return scraper.request.init()
}

@DslMarker
annotation class SkrapeItDslMarker

typealias Doc = Document
