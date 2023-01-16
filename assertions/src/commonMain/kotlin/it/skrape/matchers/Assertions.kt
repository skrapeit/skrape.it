package it.skrape.matchers

import io.ktor.http.*

public fun HttpStatusCode.statusAssertion(value: Boolean, status: HttpStatusCode): HttpStatusCode {
    return this.also { generalAssertion(value, status) }
}

public fun Any?.generalAssertion(value: Boolean, expected: Any?, message: String = "is equal to") {
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
