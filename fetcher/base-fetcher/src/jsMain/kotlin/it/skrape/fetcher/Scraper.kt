package it.skrape.fetcher

import it.skrape.SkrapeItDsl
import kotlinx.coroutines.*

/**
 * Create a scraper config with given parameters or defaults.
 * @return Result
 */
@SkrapeItDsl
actual fun <R, T> skrape(
    fetcher: BlockingFetcher<R>,
    init: suspend Scraper<R>.() -> T
): dynamic {
    val job = GlobalScope.async(start = CoroutineStart.UNDISPATCHED) { Scraper(FetcherConverter(fetcher)).init() }
    return job.asPromise()
}