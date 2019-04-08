package it.skrape.core.fetcher

import it.skrape.core.Request
import it.skrape.core.Result
import org.jsoup.Connection
import org.jsoup.Jsoup

internal class HttpFetcher(private val request: Request) {

    fun fetch(): Result {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/certs/java/cacerts")

        val connection = Jsoup.connect(request.url)
                .method(request.method)
                .userAgent(request.userAgent)
                .timeout(request.timeout)
                .headers(request.headers)
                .followRedirects(request.followRedirects)
                .ignoreContentType(request.ignoreContentType)
                .ignoreHttpErrors(request.ignoreHttpErrors)
                .validateTLSCertificates(request.validateTLSCertificates)
                .maxBodySize(request.maxBodySize)

        val response =  connection.execute()

        return Result(
                body = response.body(),
                statusCode = response.statusCode(),
                statusMessage = response.statusMessage(),
                contentType = response.contentType(),
                headers = response.headers(),
                request = request
        )
    }
}

typealias Response = Connection.Response
