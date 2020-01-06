package it.skrape.core.fetcher

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class AuthenticationTest {

    @Test
    fun `can convert username and password to valid authentication header`() {
        val authenticationHeader = Authentication(
                type = Authentication.Type.BASIC,
                username = "foo",
                password = "bar"
        ).toHeader()

        expectThat(authenticationHeader.keys.first()).isEqualTo("Authorization")
        expectThat(authenticationHeader.values.first()).isEqualTo("Basic Zm9vOmJhcg==")
    }
}