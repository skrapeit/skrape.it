package it.skrape.fetcher

public expect object BrowserFetcher : BlockingFetcher<Request> {

    public fun render(html: String): String

}
