package it.skrape.fetcher

import it.skrape.SkrapeItDsl

/**
 * This object is representing the result of an request
 * @param responseBody - the response responseBody
 * @param responseStatus - the http responses status code and message
 * @param contentType - the http responses content type
 * @param headers - the http responses headers
 * @param cookies - the http response's cookies
 */
@Suppress("LongParameterList")
@SkrapeItDsl
public class Result(
    public val responseBody: String,
    public val responseStatus: Status,
    public val contentType: ContentType,
    public val headers: Map<String, String>,
    public val baseUri: String = "",
    public val cookies: List<Cookie>
) {
    /**
     * Will return a certain response headers value
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
     * @param name that represents the
     * @return String with value of a certain response header or null
     */
    public infix fun httpHeader(name: String): String? = this.headers[name]

    public fun httpHeaders(init: Map<String, String>.() -> Unit): Map<String, String> {
        headers.apply(init)
        return headers
    }

    public fun httpHeader(name: String, init: String?.() -> Unit): String? {
        val header = headers[name]
        header.apply(init)
        return header
    }

    public fun <T> status(init: Status.() -> T): T {
        return responseStatus.init()
    }

    public fun cookies(init: List<Cookie>.() -> Unit): List<Cookie> =
            cookies.apply(init)

    @SkrapeItDsl
    public data class Status(
            val code: Int,
            val message: String
    )
}

public typealias ContentType = String?
