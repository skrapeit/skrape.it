package it.skrape.dsl

import it.skrape.core.Request
import it.skrape.core.Result
import it.skrape.core.Scraper

fun skrape(init: Request.() -> Unit): Result {
    val scraper = Scraper()
    scraper.request.init()
    return scraper.scrape()
}
