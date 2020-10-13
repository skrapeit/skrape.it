package it.skrape.core.fetcher

public interface Fetcher<T> {
    public fun fetch(request: T): Result

    public val requestBuilder: T
}
