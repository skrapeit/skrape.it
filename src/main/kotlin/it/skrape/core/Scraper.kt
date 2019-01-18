package it.skrape.core

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class Scraper(var options: Options = Options()) {

    fun scrape(): Result {
        return options.scrape()
    }

    fun read(pathToFile: String): Doc {
        val input = File(pathToFile)
        return Jsoup.parse(input, "UTF-8", "http://skrape.it/")
    }

    fun parse(html: String): Doc {
        return Jsoup.parse(html)
    }

    data class Result(
            val document: Doc,
            val response: Response
    )
}

typealias Doc = Document
