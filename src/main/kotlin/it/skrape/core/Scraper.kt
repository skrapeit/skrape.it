package it.skrape.core

import org.jsoup.nodes.Document

internal class Scraper(val request: Request = Request()) {

    fun scrape(): Result {
        val response = Fetcher(request).fetch()
        return Result(response = response, request = request)
    }
}

typealias Doc = Document
