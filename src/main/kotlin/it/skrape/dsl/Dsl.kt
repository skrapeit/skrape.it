package it.skrape.dsl

import it.skrape.core.Scraper

fun skrape(init: Scraper.Options.() -> Unit): Scraper.Result {
    val scraper = Scraper()
    scraper.options.init()
    return scraper.scrape()
}
