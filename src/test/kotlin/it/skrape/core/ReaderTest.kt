package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class ReaderTest {

    @Test
    internal fun `can read html from file system`() {
        val document = Reader(pathToFile = "src/test/resources/__files/example.html").read()
        assertThat(document.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read html from file system using the DSL`() {
        skrape {
            file = "src/test/resources/__files/example.html"
            document {
                assertThat(title()).isEqualTo("i'm the title")
            }
        }
    }
}