package it.skrape.fetcher

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AuthenticationKtTest {

    @Test
    fun `can create Authorization header for Basic-Auth via DSL`() {
        val authenticationHeader = basic {
            username = "user"
            password = "secret"
        }.toHeaderValue()

        expectThat(authenticationHeader).isEqualTo("Basic dXNlcjpzZWNyZXQ=")
    }
}