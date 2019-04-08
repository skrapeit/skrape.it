package it.skrape.core

import it.skrape.core.fetcher.BrowserFetcher
import it.skrape.core.fetcher.HttpFetcher
import org.jsoup.nodes.Document


class Scraper(val request: Request = Request()) {

    fun scrape(): Result {
        return when (request.mode) {
            Mode.BROWSER -> BrowserFetcher(request).fetch()
            Mode.HTTP -> HttpFetcher(request).fetch()
        }
    }
}

typealias Doc = Document

