package it.skrape.core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.ktor.utils.io.bits.*
import io.ktor.utils.io.core.*
import io.ktor.utils.io.streams.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.ByteBuffer
import kotlin.test.Test

class ParserTestJVM {
    @Test
    fun `can read html from file`() = runTest  {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse)
        parsedFile.titleText.shouldBe("i'm the title")
    }


    @Test
    fun `can read html from file and invoke document lambda`() = runTest  {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse) {
            titleText.shouldBe("i'm the title")
        }
    }

    @Test
    fun `can read html from file with custom charset`() = runTest  {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        parsedFile.titleText.shouldBe("i'm the title")
    }

    @Test
    fun `can read html from file with custom charset and invoke document lambda`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            titleText.shouldBe("i'm the title")
        }
    }

    @Test
    fun `will throw exception if file not found`() {
        shouldThrow<FileNotFoundException> {
            htmlDocument(File("invalid"))
        }
    }

    @Test
    fun `will throw exception if file not found and invoke document lambda`() {
        shouldThrow<FileNotFoundException> {
            htmlDocument(File("invalid")) {}
        }
    }
}

actual suspend fun getInputFromFile(location: String): Input = ParserTestJVM::class.java.getResourceAsStream("/__files/$location").asInput()
actual suspend fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()