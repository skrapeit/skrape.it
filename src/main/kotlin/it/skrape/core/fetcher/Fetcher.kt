package it.skrape.core.fetcher

import it.skrape.core.Result

interface Fetcher {
    fun fetch(): Result
}
