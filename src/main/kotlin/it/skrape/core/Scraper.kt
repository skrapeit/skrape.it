package it.skrape.core

import it.skrape.core.fetcher.BrowserFetcher
import it.skrape.core.fetcher.HttpFetcher
import it.skrape.core.fetcher.Mode
import it.skrape.core.fetcher.Request
import it.skrape.core.fetcher.Result
import java.lang.System.setProperty


class Scraper(val request: Request = Request()) {

    fun scrape(): Result = when (request.mode) {
        Mode.DOM -> BrowserFetcher(request).fetch()
        Mode.SOURCE -> HttpFetcher(request).fetch()
    }
}
