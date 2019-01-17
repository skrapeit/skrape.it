package core

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class Skraper {

    var params: Skraper.Params = Params()

    fun fetch(): Result {
        return params.fetch()
    }

    fun read(pathToFile: String): Document {
        val input = File(pathToFile)
        return Jsoup.parse(input, "UTF-8", "http://skrape.it/")

    }

    fun parse(html: String): Document {
        return Jsoup.parse(html)
    }

    data class Result(
            val document: Document,
            val response: Connection.Response
    )

    data class Params(
            var url: String = "http://localhost:8080",
            var method: HttpMethod = HttpMethod.GET,
            var userAgent: String = "Mozilla/5.0 skrape.it",
            var timeout: Int = 5000,
            var followRedirects: Boolean = true,
            var ignoreContentType: Boolean = true,
            var ignoreHttpErrors: Boolean = true,
            var validateTLSCertificates: Boolean = false,
            var headers: Map<String, String> = mutableMapOf()
    ) {
        fun fetch(): Result {
            val response = Fetcher(this).fetch()

            val document = response.parse()

            return Result(document, response)
        }
    }

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
}
