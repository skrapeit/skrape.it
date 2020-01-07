package it.skrape.core.fetcher

import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo

internal class AuthenticationKtTest {

    @Test
    fun `can create Authorization header for Basic-Auth via DSL`() {
        val auth = basic {
            username = "user"
            password = "secret"
        }

        expect {
           that(auth.type).isEqualTo(Authentication.Type.BASIC)
           that(auth.username).isEqualTo("user")
           that(auth.password).isEqualTo("secret")
        }
    }

    @Test
    fun `authentication header will be empty by default`() {
        val authenticationHeader = Authentication().toHeaderValue()

        expectThat(authenticationHeader).isEmpty()
    }

    @Test
    fun `can convert username and password to valid authentication header`() {
        val authenticationHeader = Authentication(
                type = Authentication.Type.BASIC,
                username = "foo",
                password = "bar"
        ).toHeaderValue()

        expectThat(authenticationHeader).isEqualTo("Basic Zm9vOmJhcg==")
    }
}