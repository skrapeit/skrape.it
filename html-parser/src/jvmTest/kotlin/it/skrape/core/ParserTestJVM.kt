package it.skrape.core

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
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
        expect(parsedFile.titleText).toEqual("i'm the title")
    }


    @Test
    fun `can read html from file and invoke document lambda`() = runTest  {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse) {
            expect(titleText).toEqual("i'm the title")
        }
    }

    @Test
    fun `can read html from file with custom charset`() = runTest  {
        val fileToParse = getInputFromFile("example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        expect(parsedFile.titleText).toEqual("i'm the title")
    }

    @Test
    fun `can read html from file with custom charset and invoke document lambda`() = runTest {
        val fileToParse = getInputFromFile("example.html")
        htmlDocument(fileToParse, charset = Charsets.ISO_8859_1) {
            expect(titleText).toEqual("i'm the title")
        }
    }

    @Test
    fun `will throw exception if file not found`() {
        expect {
            htmlDocument(File("invalid"))
        }.toThrow<FileNotFoundException>()
    }

    @Test
    fun `will throw exception if file not found and invoke document lambda`() {
        expect {
            htmlDocument(File("invalid")) {}
        }.toThrow<FileNotFoundException>()
    }
}

actual suspend fun getInputFromFile(location: String): Input = ParserTestJVM::class.java.getResourceAsStream("/__files/$location").asInput()
actual suspend fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()