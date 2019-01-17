package it.skrape.core

import it.skrape.Skrape
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class Scraper(var options: Scraper.Options = Options()) : Skrape {

    override fun fetch(): Result {
        return options.fetch()
    }

    override fun read(pathToFile: String): Doc {
        val input = File(pathToFile)
        return Jsoup.parse(input, "UTF-8", "http://skrape.it/")

    }

    override fun parse(html: String): Doc {
        return Jsoup.parse(html)
    }

    data class Result(
            val document: Doc,
            val response: Response
    )

    data class Options(
            var url: String = "http://localhost:8080",
            var method: Method = Method.GET,
            var userAgent: String = "Mozilla/5.0 skrape.it",
            var timeout: Int = 5000,
            var followRedirects: Boolean = true,
            var ignoreContentType: Boolean = true,
            var ignoreHttpErrors: Boolean = true,
            var validateTLSCertificates: Boolean = false,
            var headers: Map<String, String> = mutableMapOf()
    ) {
        fun fetch(): Result {
            val response = Fetcher(this).fetch()

            val document = response.parse()

            return Result(document, response)
        }
    }
}

typealias Doc = Document
typealias Method = Connection.Method
