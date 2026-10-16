package it.skrape.fetcher

import kotlinx.coroutines.runBlocking

public object HttpFetcher : BlockingFetcher<Request> {

    override val requestBuilder: Request get() = Request()

    override fun fetch(request: Request): Result = runBlocking { AsyncFetcher.fetch(request) }
}
