package it.skrape.fetcher

import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.Cookie
import com.gargoylesoftware.htmlunit.util.NameValuePair
import java.net.Proxy
import java.net.URL

public object BrowserFetcher : BlockingFetcher<Request> {
    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result = WebClient(BrowserVersion.BEST_SUPPORTED).use { client ->
        client.withOptions(request)
        val webRequest = WebRequest(URL(request.url), request.method.toHttpMethod()).apply {
            request.body?.let { requestBody = it }
        }
        val page: Page = client.getPage(client.currentWindow.topWindow, webRequest)
        val httpResponse = page.webResponse

        Result(
            responseBody = if (page.isHtmlPage) (page as HtmlPage).asXml() else httpResponse.contentAsString,
            responseStatus = Result.Status(httpResponse.statusCode, httpResponse.statusMessage),
            contentType = httpResponse.contentType,
            headers = httpResponse.responseHeaders.toMap(),
            baseUri = request.url,
            cookies = httpResponse.responseHeaders
                .filter { it.name == "Set-Cookie" }
                .map { it.value.toCookie(request.url.urlOrigin) }
        )
    }

    public fun render(html: String): String = WebClient(BrowserVersion.BEST_SUPPORTED).use { client ->
        client.loadHtmlCodeIntoCurrentWindow(html).asXml()
    }

    private fun WebClient.withOptions(request: Request) = apply {
        cssErrorHandler = SilentCssErrorHandler()
        ajaxController = NicelyResynchronizingAjaxController()
        createCookies(request)
        addRequestHeader("User-Agent", request.userAgent)
        request.authentication?.let { addRequestHeader("Authorization", it.toHeaderValue()) }
        request.headers.forEach {
            addRequestHeader(it.key, it.value)
        }
        @Suppress("MagicNumber") waitForBackgroundJavaScript(10_000)
        options.apply {
            timeout = request.timeout
            isRedirectEnabled = request.followRedirects
            maxInMemory = 0
            isUseInsecureSSL = request.sslRelaxed
            isCssEnabled = false
            isPopupBlockerEnabled = true
            isDownloadImages = false
            isThrowExceptionOnScriptError = false
            isThrowExceptionOnFailingStatusCode = false
            isPrintContentOnFailingStatusCode = false
            historySizeLimit = 0
            historyPageCacheLimit = 0
            withProxySettings(request)
        }
    }

    private fun WebClientOptions.withProxySettings(request: Request) {
        request.proxy?.let { proxy ->
            proxyConfig = ProxyConfig(proxy.host, proxy.port, "http", proxy.type == Proxy.Type.SOCKS)
        }
    }

    private fun WebClient.createCookies(request: Request) {
        val domain = URL(request.url).host
        request.cookies.forEach { (name, value) -> cookieManager.addCookie(Cookie(domain, name, value)) }
    }

}

public fun Map<String, String>.asRawCookieSyntax(): String =
        entries.joinToString(";", postfix = ";") { "${it.key}=${it.value}" }

public fun List<NameValuePair>.toMap(): Map<String, String> =
        associateBy({ it.name }, { it.value })

internal fun Method.toHttpMethod(): HttpMethod = when (this) {
    Method.GET -> HttpMethod.GET
    Method.POST -> HttpMethod.POST
    Method.HEAD -> HttpMethod.HEAD
    Method.DELETE -> HttpMethod.DELETE
    Method.PATCH -> HttpMethod.PATCH
    Method.PUT -> HttpMethod.PUT
}
