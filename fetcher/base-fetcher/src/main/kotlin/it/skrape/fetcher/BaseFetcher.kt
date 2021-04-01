package it.skrape.fetcher

public interface BlockingFetcher<T> {
    public fun fetch(request: T): Result
    public val requestBuilder: T
}

public interface NonBlockingFetcher<T> {
    public suspend fun fetch(request: T): Result
    public val requestBuilder: T
}
