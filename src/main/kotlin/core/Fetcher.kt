package core

import org.jsoup.Connection
import org.jsoup.Jsoup

internal class Fetcher(
        private val params: Skraper.Params
) {

    fun fetch(): Connection.Response {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/certs/java/cacerts")

        val request = Jsoup.connect(params.url)
                .method(params.method.verb)
                .userAgent(params.userAgent)
                .timeout(params.timeout)
                .headers(params.headers)
                .followRedirects(params.followRedirects)
                .ignoreContentType(params.ignoreContentType)
                .ignoreHttpErrors(params.ignoreHttpErrors)
                .validateTLSCertificates(params.validateTLSCertificates)
                .maxBodySize(0)

        return request.execute()
    }
}
