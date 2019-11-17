package it.skrape.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File
import java.io.FileNotFoundException

internal class ReaderTest {

    @Test
    internal fun `can read html from file`() {
        val parsedFile = Reader(File("src/test/resources/__files/example.html")).read()
        expectThat(parsedFile.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `can read html from file with custom charset`() {
        val parsedFile = Reader(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1).read()
        expectThat(parsedFile.title()).isEqualTo("i'm the title")
    }

    @Test
    internal fun `will throw exception if file not found`() {
        Assertions.assertThrows(FileNotFoundException::class.java) {
            Reader(File("invalid")).read()
        }
    }
}