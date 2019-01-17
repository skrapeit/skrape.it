package it.skrape.core

import org.jsoup.Connection
import org.jsoup.Jsoup

internal class Fetcher(
        private val options: Scraper.Options
) {

    fun fetch(): Response {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/certs/java/cacerts")

        val request = Jsoup.connect(options.url)
                .method(options.method)
                .userAgent(options.userAgent)
                .timeout(options.timeout)
                .headers(options.headers)
                .followRedirects(options.followRedirects)
                .ignoreContentType(options.ignoreContentType)
                .ignoreHttpErrors(options.ignoreHttpErrors)
                .validateTLSCertificates(options.validateTLSCertificates)
                .maxBodySize(0)

        return request.execute()
    }
}

typealias Response = Connection.Response
