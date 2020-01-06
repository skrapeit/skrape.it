package it.skrape.core.fetcher

import it.skrape.core.fetcher.Authentication.Type.*
import it.skrape.core.fetcher.Method.GET
import it.skrape.core.fetcher.Mode.SOURCE
import it.skrape.core.fetcher.Protocol.HTTP

data class Request(

        /**
         * Defines the request-mode.
         * Defaults to SOURCE.
         * @see Mode for all possible values.
         */
        var mode: Mode = SOURCE,

        /**
         * Defines the http verb of the request.
         * Defaults to GET.
         * @see Method for all possible values.
         */
        var method: Method = GET,

        /**
         * Defines the protocol of the URL the request is made against.
         * Defaults to HTTP.
         * @see Protocol for all possible values.
         */
        var protocol: Protocol = HTTP,

        /**
         * Defines the hostname of the URL the request is made against.
         * Defaults to "localhost"
         */
        var host: String = "localhost",

        /**
         * Defines the port of the URL the request is made against.
         * Defaults to 8080
         */
        var port: Int = 8080,

        /**
         * Defines the path of the URL the request is made against.
         * Defaults to "/"
         */
        var path: String = "/",

        var queryParam: Map<String, String> = emptyMap(),

        /**
         * Defines the URL the request is made against.
         * Defaults to "http://localhost:8080/"
         * If you set this parameter other url-specific parameters (protocol, host, port, path, queryParam)
         * will have no effect.
         */
        var url: String = "${protocol.value}$host:$port$path${queryParam.toUrlQuery()}",

        var userAgent: String = "Mozilla/5.0 skrape.it",
        var headers: Map<String, String> = emptyMap(),
        var cookies: Map<String, String> = emptyMap(),
        var timeout: Int = 5000,
        var followRedirects: Boolean = true,
        var authentication: Authentication = Authentication(NONE)
) {
    fun asConfig() = this

}

private fun Map<String, String>.toUrlQuery() =
        if (!isEmpty()) {
            entries.joinToString(separator = "&", prefix = "?") {
                "${it.key}=${it.value}"
            }
        } else ""

enum class Mode {
    /**
     * Use SOURCE to get server-side rendered responses.
     * If SOURCE-mode is active skrape{it} will behave like normal http-client.
     */
    SOURCE,
    /**
     * Use DOM to get client-side rendered responses.
     * If DOM-mode is active skrape{it} will use a browser engine to transform the response body to a rendered document.
     */
    DOM
}

enum class Method {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    HEAD
}

enum class Protocol(val value: String) {
    HTTP("http://"),
    HTTPS("https://"),
    FTP("ftp://"),
    FILE("file://")
}
