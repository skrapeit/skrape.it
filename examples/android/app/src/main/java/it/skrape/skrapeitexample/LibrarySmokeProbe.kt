package it.skrape.skrapeitexample

import androidx.annotation.Keep
import it.skrape.core.htmlDocument
import it.skrape.fetcher.AsyncFetcher
import it.skrape.fetcher.BrowserFetcher
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import kotlinx.coroutines.runBlocking

// Keep only this instrumentation entry point. R8 can shrink and optimize the library itself.
@Keep
object LibrarySmokeProbe {
    fun parse(html: String): String = htmlDocument(html) { titleText }

    fun fetch(url: String): String = skrape(HttpFetcher) {
        request { this.url = url }
        response { htmlDocument { titleText } }
    }

    fun fetchAsync(url: String): String = runBlocking {
        skrape(AsyncFetcher) {
            request { this.url = url }
            response { htmlDocument { titleText } }
        }
    }

    fun renderJavaScript(html: String): String = htmlDocument(BrowserFetcher.render(html)) {
        "#rendered" { findFirst { text } }
    }
}
