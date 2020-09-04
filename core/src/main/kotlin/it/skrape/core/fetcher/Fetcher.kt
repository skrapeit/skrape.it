package it.skrape.core.fetcher

interface Fetcher<T> {
    fun fetch(request: T): Result

    val requestBuilder: T
}
