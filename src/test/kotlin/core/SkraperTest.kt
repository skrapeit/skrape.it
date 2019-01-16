package core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class SkraperTest {

    @Test
    fun `can parse html from file system`() {
        val document = Skraper().read("src/test/resources/__files/example.html")
        assertThat(document.title()).isEqualTo("i'm the title")
    }
}
