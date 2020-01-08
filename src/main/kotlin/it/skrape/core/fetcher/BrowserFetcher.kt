package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.Cookie
import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.core.fetcher.Method.GET
import it.skrape.exceptions.UnsupportedRequestOptionException
import java.net.URL

class BrowserFetcher(private val request: Request) : Fetcher {

    override fun fetch(): Result {

        if (request.method != GET)
            throw UnsupportedRequestOptionException("Browser mode only supports the http verb GET")

        val client = WebClient(BrowserVersion.BEST_SUPPORTED).withOptions()

        val page: Page = client.getPage(request.url)
        val httpResponse = page.webResponse
        val document = when {
            page.isHtmlPage -> (page as HtmlPage).asXml()
            else -> httpResponse.contentAsString
        }
        val headers = httpResponse.responseHeaders.toMap()

        val result = Result(
                responseBody = document,
                statusCode = httpResponse.statusCode,
                statusMessage = httpResponse.statusMessage,
                contentType = httpResponse.contentType,
                headers = headers,
                request = request
        )

        client.javaScriptEngine.shutdown()
        client.close()
        client.cache.clear()
        page.cleanUp()
        return result
    }

    private fun WebClient.withOptions() = apply {
        cssErrorHandler = SilentCssErrorHandler()
        ajaxController = NicelyResynchronizingAjaxController()
        createCookies()
        addRequestHeader("User-Agent", request.userAgent)
        if (request.authentication != null) {
            addRequestHeader("Authorization",request.authentication!!.toHeaderValue())
        }
        request.headers.forEach {
            addRequestHeader(it.key, it.value)
        }
        @Suppress("MagicNumber") waitForBackgroundJavaScript(10_000)
        options.apply {
            timeout = request.timeout
            isRedirectEnabled = request.followRedirects
            maxInMemory = 0
            isUseInsecureSSL = true
            isCssEnabled = false
            isPopupBlockerEnabled = true
            isDownloadImages = false
            isThrowExceptionOnScriptError = false
            isThrowExceptionOnFailingStatusCode = false
            isPrintContentOnFailingStatusCode = false
            historySizeLimit = 0
            historyPageCacheLimit = 0
        }
    }

    private fun WebClient.createCookies() {
        request.cookies.forEach { cookieManager.addCookie(createCookie(it.key, it.value)) }
    }

    private fun createCookie(name: String, value: String): Cookie {
        val domain = URL(request.url).host
        return Cookie(domain, name, value)
    }
}

fun Map<String, String>.asRawCookieSyntax(): String {
    var result = ""
    forEach { result += "${it.key}=${it.value};" }
    return result
}

fun List<NameValuePair>.toMap(): Map<String, String> =
        associateByTo(mutableMapOf<String, String>(), { it.name }, { it.value })
