package it.skrape.core

import org.jsoup.nodes.Document

class Scraper(val request: Request = Request()) {

    fun scrape(): Result {
        val response = Fetcher(request).fetch()
        return Result(response)
    }
}

typealias Doc = Document
