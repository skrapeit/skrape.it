package it.skrape.core

import it.skrape.SkrapeItDsl
import it.skrape.core.fetcher.*

class Scraper<R>(val client: Fetcher<R>, internal val preparedRequest: R) {
    constructor(client: Fetcher<R>) : this(client, client.requestBuilder)

    @SkrapeItDsl
    fun request(init: R.() -> Unit) =
            this.preparedRequest.run(init)

    fun scrape() =
            client.fetch(preparedRequest)

    @SkrapeItDsl
    val preConfigured
        get() = this
}
