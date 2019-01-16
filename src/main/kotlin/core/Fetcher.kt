package core

import org.jsoup.Connection
import org.jsoup.Jsoup

class Fetcher(private val params: Params = Params()) {

    fun fetch(): Connection.Response {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true")
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/certs/java/cacerts")

        val request = Jsoup.connect(params.url)
                .method(params.method.verb)
                .userAgent(params.userAgent)
                .timeout(params.timeout)
                .followRedirects(params.followRedirects)
                .ignoreContentType(params.ignoreContentType)
                .ignoreHttpErrors(params.ignoreHttpErrors)
                .validateTLSCertificates(params.validateTLSCertificates)
                .maxBodySize(0)

        return request.execute()
    }
}

data class Params(
        val url: String = "http://localhost:8080",
        val method: HttpMethod = HttpMethod.GET,
        val userAgent: String = "Mozilla/5.0 skrape.it/0-SNAPSHOT",
        val timeout: Int = 5000,
        val followRedirects: Boolean = true,
        val ignoreContentType: Boolean = true,
        val ignoreHttpErrors: Boolean = true,
        val validateTLSCertificates: Boolean = false
)

enum class HttpMethod(val verb: Connection.Method) {
    GET(Connection.Method.GET),
    POST(Connection.Method.POST),
    DELETE(Connection.Method.DELETE),
    PUT(Connection.Method.PUT),
    PATCH(Connection.Method.PATCH),
    HEAD(Connection.Method.HEAD),
    OPTIONS(Connection.Method.OPTIONS),
    TRACE(Connection.Method.TRACE)
}
