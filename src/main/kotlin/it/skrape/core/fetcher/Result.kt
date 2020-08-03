package it.skrape.core.fetcher

import it.skrape.SkrapeItDsl

/**
 * This object is representing the result of an request
 * @param responseBody - the response responseBody
 * @param statusCode - the http responses status code
 * @param statusMessage - the http responses status message
 * @param contentType - the http responses content type
 * @param headers - the http responses headers
 * @param request - the initial request
 * @param cookies - the http response's cookies
 */
@Suppress("LongParameterList")
@SkrapeItDsl
class Result(
        val request: Request,
        val responseBody: String,
        val responseStatus: Status,
        val contentType: ContentType,
        val headers: Map<String, String>,
        val cookies: List<Cookie>
) {
    /**
     * Will return a certain response headers value
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
     * @param name that represents the
     * @return String with value of a certain response header or null
     */
    infix fun httpHeader(name: String): String? = this.headers[name]

    fun httpHeaders(init: Map<String, String>.() -> Unit): Map<String, String> {
        headers.apply(init)
        return headers
    }

    fun httpHeader(name: String, init: String?.() -> Unit): String? {
        val header = headers[name]
        header.apply(init)
        return header
    }

    fun <T> status(init: Status.() -> T): T {
        return responseStatus.init()
    }

    @SkrapeItDsl
    data class Status(
            val code: Int,
            val message: String
    )
}

typealias ContentType = String?
