package it.skrape.fetcher

public interface AsyncFetcher<T> {
    public suspend fun fetch(request:T):Result

    public val requestBuilder: T
}
