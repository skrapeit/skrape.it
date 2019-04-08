package it.skrape.core.fetcher

import com.machinepublishers.jbrowserdriver.JBrowserDriver
import com.machinepublishers.jbrowserdriver.Settings
import com.machinepublishers.jbrowserdriver.UserAgent
import it.skrape.core.Request
import it.skrape.core.Result
import org.jsoup.Connection


class BrowserFetcher(private val request: Request) {

    fun fetch(): Result {

        if (request.method != Connection.Method.GET)
            throw UnsupportedRequestOptionException("Browser mode only supports the http verb GET")
        if (!request.followRedirects)
            throw UnsupportedRequestOptionException("Browser mode only supports following redirects")

        val driver = JBrowserDriver(Settings.builder()
            .ssl("trustanything")
            .javaOptions("-Djsse.enableSNIExtension=false")
            .blockAds(true)
            .blockMedia(true)
            .ignoreDialogs(true)
            .quickRender(true)
            .headless(true)
            .userAgent(UserAgent(UserAgent.Family.MOZILLA, "", "", "", "", request.userAgent))
            .socketTimeout(request.timeout)
            .connectionReqTimeout(request.timeout * 2)
            .hostnameVerification(false).build())

        driver.get(request.url)

        val result = Result(
                body = driver.pageSource,
                statusCode = driver.statusCode,
                statusMessage = null,
                contentType = null,
                headers = emptyMap(),
                request = request
        )

        driver.quit()
        return result
    }
}

class UnsupportedRequestOptionException(message:String): IllegalArgumentException(message)
