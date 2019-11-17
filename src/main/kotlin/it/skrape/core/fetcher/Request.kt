package it.skrape.core.fetcher

import it.skrape.SkrapeItDsl
import it.skrape.core.fetcher.Method.GET
import it.skrape.core.fetcher.Mode.SOURCE
import it.skrape.core.fetcher.Protocol.HTTP

data class Request(

        /**
         * Defines the request-mode.
         * Defaults to SOURCE.
         * @see it.skrape.core.Mode for all possible values.
         */
        @SkrapeItDsl var mode: Mode = SOURCE,

        /**
         * Defines the http verb of the request.
         * Defaults to GET.
         * @see it.skrape.core.Method for all possible values.
         */
        @SkrapeItDsl var method: Method = GET,

        /**
         * Defines the protocol of the URL the request is made against.
         * Defaults to HTTP.
         * @see it.skrape.core.Protocol for all possible values.
         */
        @SkrapeItDsl var protocol: Protocol = HTTP,

        /**
         * Defines the hostname of the URL the request is made against.
         * Defaults to "localhost"
         */
        @SkrapeItDsl var host: String = "localhost",

        /**
         * Defines the port of the URL the request is made against.
         * Defaults to 8080
         */
        @SkrapeItDsl var port: Int = 8080,

        /**
         * Defines the path of the URL the request is made against.
         * Defaults to "/"
         */
        @SkrapeItDsl var path: String = "/",

        /**
         * Defines the URL the request is made against.
         * Defaults to "http://localhost:8080/"
         * If you set this parameter other url-specific parameters (protocol, host, port, path)
         * will have no effect.
         */
        @SkrapeItDsl
        var url: String = "${protocol.value}$host:$port$path",

        @SkrapeItDsl var userAgent: String = "Mozilla/5.0 skrape.it",
        @SkrapeItDsl var headers: Map<String, String> = emptyMap(),
        @SkrapeItDsl var cookies: Map<String, String> = emptyMap(),
        @SkrapeItDsl var timeout: Int = 5000,
        @SkrapeItDsl var followRedirects: Boolean = true
)

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
