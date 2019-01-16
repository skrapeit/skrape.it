package core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class FetcherTest {

    @Test
    fun `can fetch url`() {
        val response = Fetcher(Params("http://www.google.de")).fetch()
        assertThat(response.statusCode()).isEqualTo(200)
        assertThat(response.parse().title()).isEqualTo("Google")
    }
}
