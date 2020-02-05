package it.skrape.core.fetcher

import it.skrape.SkrapeItDsl
import it.skrape.selects.Doc
import it.skrape.core.Parser
import it.skrape.core.htmlDocument

/**
 * This object is representing the result of an request
 * @param responseBody - the response responseBody
 * @param document - the already parsed page-source
 * @param statusCode - the http responses status code
 * @param statusMessage - the http responses status message
 * @param contentType - the http responses content type
 * @param headers - the http responses headers
 * @param request - the initial request
 */
@SkrapeItDsl
class Result(
        val request: Request,
        val responseBody: ResponseBody,
        val document: Doc = htmlDocument(html = responseBody, baseUri = request.url),
        val statusCode: StatusCode,
        val statusMessage: StatusMessage,
        val contentType: ContentType,
        val headers: Headers
) {
    /**
     * Will return a certain response headers value
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Response_header">Explanation about response headers.</a>
     * @param name that represents the
     * @return String with value of a certain response header or null
     */
    infix fun httpHeader(name: String): String? = this.headers[name]

    fun httpHeaders(init: Headers.() -> Unit): Map<String, String> {
        headers.apply(init)
        return headers
    }

    fun httpHeader(name: String, init: String?.() -> Unit): String? {
        val header = headers[name]
        header.apply(init)
        return header
    }

    fun <T> htmlDocument(init: Doc.() -> T) = document.init()
}

typealias ResponseBody = String
typealias StatusCode = Int
typealias StatusMessage = String?
typealias ContentType = String?
typealias Headers = Map<String, String>
