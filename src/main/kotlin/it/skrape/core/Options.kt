package it.skrape.core

import org.jsoup.Connection

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
    fun scrape(): Scraper.Result {
        val response = Fetcher(this).fetch()
        val document = response.parse()
        return Scraper.Result(document, response)
    }

    fun result(init: Scraper.Result.() -> Unit): Scraper.Result {
        val result = Scraper(this).scrape()
        result.init()
        return result
    }
}

typealias Method = Connection.Method