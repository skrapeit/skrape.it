package it.skrape.core

import it.skrape.core.fetcher.*


class Scraper(val request: Request = Request()) {

    fun scrape(): Result {

        return when (request.mode) {
            Mode.DOM -> BrowserFetcher.fetch(request)
            Mode.SOURCE -> HttpFetcher.fetch(request)
        }
    }
}
