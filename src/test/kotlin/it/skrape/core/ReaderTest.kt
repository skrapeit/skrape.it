package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class ReaderTest {

    @Test
    internal fun `can read html from file system using the DSL`() {
        skrape {
            skrapeFile("src/test/resources/__files/example.html") {
                assertThat(title()).isEqualTo("i'm the title")
            }
        }
    }
}