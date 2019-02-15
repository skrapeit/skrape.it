package it.skrape.core

import org.jsoup.Connection

data class Request(
        var url: String = "http://localhost:8080",
        var file: String = "",
        var method: Method = Method.GET,
        var userAgent: String = "Mozilla/5.0 skrape.it",
        var headers: Map<String, String> = mutableMapOf(),
        var timeout: Int = 5000,
        var followRedirects: Boolean = true,
        var ignoreContentType: Boolean = true,
        var ignoreHttpErrors: Boolean = true,
        var validateTLSCertificates: Boolean = false,
        var maxBodySize: Int = 0
) {
    @SkrapeItDslMarker
    fun expect(init: Result.() -> Unit) {
        val result = Scraper(this).scrape()
        result.init()
    }

    @SkrapeItDslMarker
    fun document(init: Doc.() -> Unit) {
        Reader(this.file).read().init()
    }

    @SkrapeItDslMarker
    fun <T> extract(extractor: Result.() -> T): T {
        val result = Scraper(this).scrape()
        return result.extractor()
    }

    @SkrapeItDslMarker
    fun <T> extractIt(extractor: (result: Result) -> T): T {
        val result = Scraper(this).scrape()
        return extractor(result)
    }
}

typealias Method = Connection.Method