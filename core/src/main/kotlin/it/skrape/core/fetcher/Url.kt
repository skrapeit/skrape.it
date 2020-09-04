package it.skrape.core.fetcher

import it.skrape.SkrapeItDsl

@SkrapeItDsl
data class UrlBuilder(
        /**
         * Defines the protocol of the URL the request is made against.
         * Defaults to HTTP.
         * @see Protocol for all possible values.
         */
        var protocol: Protocol = Protocol.HTTP,

        /**
         * Defines the hostname of the URL the request is made against.
         * Defaults to "localhost".
         */
        var host: String = "localhost",

        /**
         * Defines the port of the URL the request is made against.
         * Defaults to 8080.
         */
        var port: Int = 8080,

        /**
         * Defines the path of the URL the request is made against.
         * Defaults to "/".
         */
        var path: String = "/",

        var queryParam: Map<String, String> = emptyMap()
) {
    override fun toString(): String = "${protocol.value}$host:$port$path${queryParam.toUrlQuery()}"

    private fun Map<String, String>.toUrlQuery() =
            if (!isEmpty()) {
                entries.joinToString(separator = "&", prefix = "?") {
                    "${it.key}=${it.value}"
                }
            } else ""

    enum class Protocol(val value: String) {
        HTTP("http://"),
        HTTPS("https://"),
        FTP("ftp://"),
        FILE("file://")
    }
}

typealias Url = String
