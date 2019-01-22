package it.skrape.core

import org.jsoup.Connection
import org.jsoup.Jsoup

class Fetcher(
        private val request: Request
) {

    fun fetch(): Response {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/certs/java/cacerts")

        val request = Jsoup.connect(request.url)
                .method(request.method)
                .userAgent(request.userAgent)
                .timeout(request.timeout)
                .headers(request.headers)
                .followRedirects(request.followRedirects)
                .ignoreContentType(request.ignoreContentType)
                .ignoreHttpErrors(request.ignoreHttpErrors)
                .validateTLSCertificates(request.validateTLSCertificates)
                .maxBodySize(0)

        return request.execute()
    }
}

typealias Response = Connection.Response
