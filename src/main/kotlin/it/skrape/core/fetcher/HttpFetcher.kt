package it.skrape.core.fetcher

import it.skrape.core.Request
import it.skrape.core.Result
import org.jsoup.Jsoup

internal class HttpFetcher(private val request: Request): Fetcher {

    override fun fetch(): Result {

        val connection = Jsoup.connect(request.url)
                .method(request.method)
                .userAgent(request.userAgent)
                .timeout(request.timeout)
                .headers(request.headers)
                .cookies(request.cookies)
                .followRedirects(request.followRedirects)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .validateTLSCertificates(false)
                .maxBodySize(0)

        val response =  connection.execute()

        return Result(
                responseBody = response.body(),
                statusCode = response.statusCode(),
                statusMessage = response.statusMessage(),
                contentType = response.contentType(),
                headers = response.headers(),
                request = request
        )
    }
}
