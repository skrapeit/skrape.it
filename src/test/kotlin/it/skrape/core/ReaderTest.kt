package it.skrape.core

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class ReaderTest {

    @Test
    internal fun `can parse html from file system`() {
        val document = Reader(pathToFile = "src/test/resources/__files/example.html").read()
        assertThat(document.title()).isEqualTo("i'm the title")
    }
}