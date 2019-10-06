package it.skrape.core

import it.skrape.core.fetcher.BrowserFetcher
import it.skrape.core.fetcher.HttpFetcher
import org.jsoup.nodes.Document


class Scraper(val request: Request = Request()) {

    fun scrape(): Result {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")

        return when (request.mode) {
            Mode.DOM -> BrowserFetcher(request).fetch()
            Mode.SOURCE -> HttpFetcher(request).fetch()
        }
    }
}

typealias Doc = Document
