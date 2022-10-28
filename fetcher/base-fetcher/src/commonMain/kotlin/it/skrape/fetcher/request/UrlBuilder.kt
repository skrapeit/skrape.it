package it.skrape.fetcher.request

import it.skrape.SkrapeItDsl
import it.skrape.fetcher.request.UrlBuilder.Protocol

@SkrapeItDsl
public data class UrlBuilder(
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
     */
    var path: String = "",

    var fragment: String? = null,
) {

    private var queryParam: String = ""

    public fun queryParam(init: QueryParam.() -> Unit) {
        queryParam = QueryParam().also(init).toString()
    }

    public enum class Protocol(public val value: String) {
        HTTP("http"),
        HTTPS("https"),
        FTP("ftp"),
        FILE("file");

        internal fun findOrDefault(value: String): Protocol = values().find { it.value == value } ?: HTTP
    }

    override fun toString(): String = buildString {
        append("${protocol.value}://")
        append(host)
        append(if (port <= 0) "" else ":$port")
        append(if (path.isNotBlank() && !path.startsWith("/")) "/$path" else path)
        if (!fragment.isNullOrBlank()) append("#$fragment")
        append(queryParam)
    }
}

@SkrapeItDsl
public class QueryParam {
    private val params: MutableMap<String, String> = mutableMapOf()
    public infix fun String.to(value: Any?) {
        // TODO: do not ignore entries with value null
        params.getOrPut(this) {
            when (value) {
                null -> "null"
                is List<*> -> value.joinToString(separator = ",")
                else -> "$value"
            }
        }
    }

    private val keyOnlyParams: MutableList<String> = mutableListOf()
    public operator fun String.unaryPlus() {
        if(this.isNotBlank()) keyOnlyParams += this
    }

    override fun toString(): String {
        return buildString {
            params.forEach { (key, value) ->
                append("&$key=$value")
            }
            keyOnlyParams.forEach {
                append("&$it")
            }
        }.replaceFirst("&", "?")
    }
}
