package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.core.Request
import it.skrape.core.Result
import it.skrape.exceptions.UnsupportedRequestOptionException
import org.jsoup.Connection

class BrowserFetcher(private val request: Request) {

    fun fetch(): Result {

        if (request.method != Connection.Method.GET)
            throw UnsupportedRequestOptionException("Browser mode only supports the http verb GET")

        val client = WebClient(BrowserVersion.BEST_SUPPORTED)
        client.apply {
            options.timeout = request.timeout
            options.isRedirectEnabled = request.followRedirects
            options.maxInMemory = request.maxBodySize
            options.isCssEnabled = false
            options.isPopupBlockerEnabled = true
            options.isDownloadImages = false
            options.isUseInsecureSSL = true
            options.isThrowExceptionOnScriptError = false
            options.isThrowExceptionOnFailingStatusCode = false
            options.isPrintContentOnFailingStatusCode = false
            options.historySizeLimit = 0
            options.historyPageCacheLimit = 0
            cssErrorHandler = SilentCssErrorHandler()
        }.apply {
            ajaxController = NicelyResynchronizingAjaxController()
            @Suppress("MagicNumber") waitForBackgroundJavaScript(10_000)
        }

        val page: Page = client.getPage(request.url)
        val httpResponse = page.webResponse
        val document = when {
            page.isHtmlPage -> (page as HtmlPage).asXml()
            else -> httpResponse.contentAsString
        }
        val headers = httpResponse.responseHeaders.toMap()

        val result = Result(
                body = document,
                statusCode = httpResponse.statusCode,
                statusMessage = httpResponse.statusMessage,
                contentType = httpResponse.contentType,
                headers = headers,
                cookies = headers.extractCookies(),
                request = request
        )

        client.javaScriptEngine.shutdown()
        client.close()
        client.cache.clear()
        page.cleanUp()
        return result
    }
}

fun List<NameValuePair>.toMap(): Map<String, String> =
        associateByTo(mutableMapOf<String, String>(), {it.name}, {it.value})

fun Map<String, String>.extractCookies(): Map<String, String> {
    val rawCookieHeader = toMap()["Cookie"] ?: return emptyMap()
    return rawCookieHeader
            .trim()
            .split(";")
            .map { it.split("=") }
            .map { it[0] to it[1] }
            .toMap()
}
