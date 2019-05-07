package it.skrape.core.fetcher

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.NameValuePair
import it.skrape.core.Request
import it.skrape.core.Result
import it.skrape.exceptions.UnsupportedRequestOptionException
import org.apache.commons.logging.LogFactory
import org.jsoup.Connection
import java.util.logging.Level
import java.util.logging.Logger.getLogger

class BrowserFetcher(private val request: Request) {

    fun fetch(): Result {

        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog")
        getLogger("com.gargoylesoftware.htmlunit").level = Level.OFF
        getLogger("org.apache.commons.httpclient").level = Level.OFF
        getLogger("com.gargoylesoftware.htmlunit.*").level = Level.OFF
        /*getLogger("com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter").level = Level.OFF
        getLogger("com.gargoylesoftware.htmlunit.javascript.host.ActiveXObject").level = Level.OFF
        getLogger("com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument").level = Level.OFF
        getLogger("com.gargoylesoftware.htmlunit.html.HtmlScript").level = Level.OFF
        getLogger("com.gargoylesoftware.htmlunit.javascript.host.WindowProxy").level = Level.OFF*/
        getLogger("org.apache").level = Level.OFF

        if (request.method != Connection.Method.GET)
            throw UnsupportedRequestOptionException("Browser mode only supports the http verb GET")

        val client = WebClient(BrowserVersion.BEST_SUPPORTED)
        client.apply {
            options.timeout = request.timeout
            options.isRedirectEnabled = request.followRedirects
            options.maxInMemory = request.maxBodySize
            options.isCssEnabled = false
            options.isJavaScriptEnabled = true
            options.isActiveXNative = false
            options.isAppletEnabled = false
            options.isPopupBlockerEnabled = true
            options.isDownloadImages = false
            options.isUseInsecureSSL = true
            options.isGeolocationEnabled = false
            options.isThrowExceptionOnScriptError = false
            options.isThrowExceptionOnFailingStatusCode = false
            options.isPrintContentOnFailingStatusCode = false
            options.historySizeLimit = 0
            options.historyPageCacheLimit = 0
            cookieManager.isCookiesEnabled = true
            cssErrorHandler = SilentCssErrorHandler()
        }.apply {
            ajaxController = NicelyResynchronizingAjaxController()
            @Suppress("MagicNumber") waitForBackgroundJavaScript(5000)
            @Suppress("MagicNumber") waitForBackgroundJavaScriptStartingBefore(1000)
        }

        val page = client.getPage<HtmlPage>(request.url)
        val response = page.webResponse
        val headers = response.responseHeaders.toMap()

        val result = Result(
                body = response.contentAsString,
                statusCode = response.statusCode,
                statusMessage = response.statusMessage,
                contentType = response.contentType,
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


