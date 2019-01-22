package it.skrape.core

import org.jsoup.nodes.Document

class Scraper(val request: Request = Request()) {

    fun scrape(): Result {
        val response = Fetcher(request).fetch()
        return Result(response)
    }
}

@SkrapeItDslMarker
fun skrape(init: Request.() -> Unit): Result {
    val scraper = Scraper()
    scraper.request.init()
    return scraper.scrape()
}

@DslMarker
annotation class SkrapeItDslMarker

typealias Doc = Document
