package it.skrape.core

import it.skrape.core.fetcher.*
import java.lang.System.setProperty


class Scraper(val request: Request = Request()) {

    fun scrape(): Result {

        return when (request.mode) {
            Mode.DOM -> BrowserFetcher(request).fetch()
            Mode.SOURCE -> HttpFetcher(request).fetch()
        }
    }
}
