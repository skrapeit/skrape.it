package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.Cookie
import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.core.fetcher.Method.GET
import it.skrape.exceptions.UnsupportedRequestOptionException
import java.net.Proxy
import java.net.URL

public object BrowserFetcher : Fetcher<Request> {
    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result {
        if (request.method != GET)
            throw UnsupportedRequestOptionException("Browser mode only supports the http verb GET")

        val client = WebClient(BrowserVersion.BEST_SUPPORTED).withOptions(request)

        val page: Page = client.getPage(request.url)
        val httpResponse = page.webResponse
        val document = when {
            page.isHtmlPage -> (page as HtmlPage).asXml()
            else -> httpResponse.contentAsString
        }
        val headers = httpResponse.responseHeaders.toMap()

        val result = Result(
                responseBody = document,
                responseStatus = httpResponse.toStatus(),
                contentType = httpResponse.contentType,
                headers = headers,
                baseUri = request.url,
                cookies = httpResponse.responseHeaders
                        .filter { it.name == "Set-Cookie" }
                        .map { it.value.toCookie(request.url.urlOrigin()) }
        )

        client.javaScriptEngine.shutdown()
        client.close()
        client.cache.clear()
        page.cleanUp()
        return result
    }

    private fun WebClient.withOptions(request: Request) = apply {
        cssErrorHandler = SilentCssErrorHandler()
        ajaxController = NicelyResynchronizingAjaxController()
        createCookies(request)
        addRequestHeader("User-Agent", request.userAgent)
        if (request.authentication != null) {
            addRequestHeader("Authorization", request.authentication!!.toHeaderValue())
        }
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

    private fun WebClientOptions.withProxySettings(request: Request): WebClientOptions {
        if (request.proxy != null) {
            this.proxyConfig = ProxyConfig(
                    request.proxy!!.host,
                    request.proxy!!.port,
                    request.proxy!!.type == Proxy.Type.SOCKS
            )
        }
        return this
    }

    private fun WebClient.createCookies(request: Request) {
        request.cookies.forEach { cookieManager.addCookie(createCookie(request.url, it.key, it.value)) }
    }

    private fun createCookie(url: String, name: String, value: String): Cookie {
        val domain = URL(url).host
        return Cookie(domain, name, value)
    }

    private fun WebResponse.toStatus() = Result.Status(statusCode, statusMessage)

}

public fun Map<String, String>.asRawCookieSyntax(): String =
        entries.joinToString(";", postfix = ";") { "${it.key}=${it.value}" }

public fun List<NameValuePair>.toMap(): Map<String, String> =
        associateBy({ it.name }, { it.value })
