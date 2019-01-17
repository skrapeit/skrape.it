package it.skrape

import it.skrape.core.Scraper
import org.jsoup.nodes.Document

interface Skrape {

    fun fetch(): Scraper.Result
    fun read(pathToFile: String): Document
    fun parse(html: String): Document

}
