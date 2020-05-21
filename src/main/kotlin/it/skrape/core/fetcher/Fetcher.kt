package it.skrape.core.fetcher

interface Fetcher {
    fun fetch(request: Request): Result
}
