package it.skrape.fetcher

public interface Fetcher<T> {
    public fun fetch(request: T): Result
    public val requestBuilder: T
}

public interface AsyncFetcher<T> {
    public suspend fun fetch(request: T): Result
    public val requestBuilder: T
}
