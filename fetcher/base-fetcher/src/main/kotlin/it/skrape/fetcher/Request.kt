package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import it.skrape.fetcher.request.BodyBuilder
import it.skrape.fetcher.request.UrlBuilder
import java.net.URI

@SkrapeItDsl
public data class Request(

    /**
     * Defines the http verb of the request.
     * Defaults to GET.
     * @see Method for all possible values.
     */
    var method: Method = Method.GET,

    /**
     * Defines the URL the request is made against.
     * Defaults to "http://localhost:8080/".
     * If you set this parameter other url-specific parameters (protocol, host, port, path, queryParam)
     * will have no effect.
     */
    var url: String = UrlBuilder().toString(),
    var proxy: ProxyBuilder? = null,

    var userAgent: String = "Mozilla/5.0 skrape.it",
    var headers: Map<String, String> = emptyMap(),
    var cookies: Map<String, String> = emptyMap(),
    var timeout: Int = 5000,
    var followRedirects: Boolean = true,
    var authentication: Authentication? = null,
    var body: String? = null,

    /**
     * Will ignore SSL by trusting all certificates.
     * This is only recommended if you have to make the client work with self-signed certificates.
     * Defaults to false.
     */
    var sslRelaxed: Boolean = false
) {
    @SkrapeItDsl
    public fun url(init: UrlBuilder.() -> Unit) {
        val formerUrl = URI(url)
        val urlBuilder = with(UrlBuilder(
        )) {
            apply {
                protocol = protocol.findOrDefault(formerUrl.scheme)
                host = formerUrl.host
                port = formerUrl.port
                path = formerUrl.path
            }
            also(init)
        }
        url = urlBuilder.toString()
    }

    @Deprecated("use url DSL instead", replaceWith = ReplaceWith("url {}", "it.skrape.fetcher.Request"))
    public fun urlBuilder(init: UrlBuilder.() -> Unit): String {
        return UrlBuilder().also(init).toString()
    }

    public fun proxyBuilder(init: ProxyBuilder.() -> Unit): ProxyBuilder = ProxyBuilder().also(init)

    public fun body(init: BodyBuilder.() -> Unit) {
        val invokedBody = BodyBuilder().also(init)
        body = invokedBody.data
        headers += "Content-Type" to invokedBody.contentType
    }
}

public enum class Method {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    HEAD
}
