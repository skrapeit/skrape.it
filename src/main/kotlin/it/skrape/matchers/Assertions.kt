package it.skrape.matchers

import it.skrape.core.fetcher.Result

fun Result.Status.statusAssertion(value: Boolean, expected: Result.Status): Result.Status {
    assertion(value) {
        """▼ Expect that Http Status
            ✗ is equal to:  "$expected" 
            ⓘ but it was:   "$this"
        """.trimMargin()
    }
    return this
}

inline fun assertion(value: Boolean, lazyMessage: () -> Any) {
    if (!value) throw AssertionError(lazyMessage())
}
