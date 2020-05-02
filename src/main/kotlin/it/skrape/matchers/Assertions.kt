package it.skrape.matchers

import it.skrape.core.fetcher.Result

fun Result.Status.statusAssertion(value: Boolean, status: Result.Status): Result.Status {
    generalAssertion(value, status)
    return this
}

fun Any?.generalAssertion(value: Boolean, expected: Any?, message: String = "is equal to") {
    assertion(value) {
        """
            ▼ Expect that  $expected
            ✗ $message:    $expected 
            ⓘ but it was:  $this
        """.trimMargin()
    }
}

private inline fun assertion(value: Boolean, lazyMessage: () -> Any) {
    if (!value) throw AssertionError(lazyMessage())
}
