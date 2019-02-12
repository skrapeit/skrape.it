package it.skrape.core

import org.jsoup.Connection

data class Request(
        var url: String = "http://localhost:8080",
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

//    @SkrapeItDslMarker
//    fun <V> extract(init: ExtractableResult<V>.() -> Unit): ExtractableResult {
//        val result = Scraper(this).scrape()
//        val extractableResult = ExtractableResult<V>(result = result, store = V)
//        extractableResult.init()
//        return ExtractableResult
//    }
}

typealias Method = Connection.Method