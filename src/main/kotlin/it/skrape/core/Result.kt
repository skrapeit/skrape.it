package it.skrape.core

import org.jsoup.Jsoup

/**
 * This object is representing the result of an request
 * @param body - the response body
 * @param document - the already parsed page-source
 * @param statusCode - the http responses status code
 * @param statusMessage - the http responses status message
 * @param contentType - the http responses status message
 * @param headers - the http responses headers
 * @param request - the initial request
 */
data class Result(
        val body: String,
        val document: Doc = Jsoup.parse(body),
        val statusCode: Int,
        val statusMessage: String?,
        val contentType: String?,
        val headers: Map<String, String>,
        val request: Request
)
