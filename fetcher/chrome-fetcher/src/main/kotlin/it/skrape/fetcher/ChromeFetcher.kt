package it.skrape.fetcher

import com.github.kklisura.cdt.launch.ChromeArguments
import com.github.kklisura.cdt.launch.ChromeLauncher
import com.github.kklisura.cdt.protocol.events.network.LoadingFinished
import com.github.kklisura.cdt.protocol.support.types.EventHandler
import com.github.kklisura.cdt.services.ChromeService
import org.htmlunit.org.apache.http.HttpStatus

public object ChromeFetcher : BlockingFetcher<Request> {
    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result {
        val chromeArgs = ChromeArguments.defaults(true)
            .additionalArguments("no-sandbox", true)
            .additionalArguments("remote-allow-origins", "*")
            .build()

        // Start Chrome
        val launcher = ChromeLauncher()
        val chromeService: ChromeService = launcher.launch(chromeArgs)
        val tab = chromeService.createTab()
        val devToolsService = chromeService.createDevToolsService(tab)

        val page = devToolsService.page
        val runtime = devToolsService.runtime

        var result: Result? = null

        // Wait for on load event
        page.onLoadEventFired { _ ->
            val evaluation = runtime.evaluate("document.documentElement.outerHTML")

            result = Result(
                responseBody = evaluation.result.value.toString(),
                responseStatus = Result.Status(HttpStatus.SC_OK, ""),
                contentType = "",
                headers = emptyMap(),
                baseUri = request.url,
                cookies = emptyList()
            )

            devToolsService.close()
        }

        page.enable()

        // Navigate to the page in question
        page.navigate(request.url)

        devToolsService.waitUntilClosed()
        chromeService.closeTab(tab)

        if (result == null) {
            throw ChromeException("No result found")
        }

        return result as Result
    }
}
