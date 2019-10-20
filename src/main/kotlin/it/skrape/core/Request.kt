package it.skrape.core

import it.skrape.core.Mode.*
import it.skrape.core.Protocol.*

data class Request(
        /**
         * Defines the request-mode
         */
        var mode: Mode = SOURCE,
        var protocol: Protocol = HTTP,
        var host: String = "localhost",
        var port: Int = 8080,
        var path: String = "/",
        var url: String = "${protocol.value}$host:$port$path",
        var method: Method = Method.GET,
        var userAgent: String = "Mozilla/5.0 skrape.it",
        var headers: Map<String, String> = emptyMap(),
        var cookies: Map<String, String> = emptyMap(),
        var timeout: Int = 5000,
        var followRedirects: Boolean = true
)

enum class Mode {
    SOURCE,
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
    HTTPS("https://")
}
