package it.skrape.core

import org.jsoup.Jsoup

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
data class Result(
        val responseBody: ResponseBody,
        val document: Doc = Jsoup.parse(responseBody),
        val statusCode: StatusCode,
        val statusMessage: StatusMessage,
        val contentType: ContentType,
        val headers: Headers,
        val request: Request
)

typealias ResponseBody = String
typealias StatusCode = Int
typealias StatusMessage = String?
typealias ContentType = String?
typealias Headers = Map<String, String>
