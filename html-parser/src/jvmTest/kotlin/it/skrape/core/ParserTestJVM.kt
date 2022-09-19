package it.skrape.core

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import io.ktor.utils.io.core.*
import io.ktor.utils.io.streams.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import kotlin.test.Test

class ParserTestJVM {
    @Test
    fun `can read html from file`() {
        val fileToParse = File("src/jvmTest/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse)
        expect(parsedFile.titleText).toEqual("i'm the title")
    }


    @Test
    fun `can read html from file and invoke document lambda`() {
        val fileToParse = File("src/jvmTest/resources/__files/example.html")
        htmlDocument(fileToParse) {
            expect(titleText).toEqual("i'm the title")
        }
    }

    @Test
    fun `can read html from file with custom charset`() {
        val fileToParse = File("src/jvmTest/resources/__files/example.html")
        val parsedFile = htmlDocument(fileToParse, charset = Charsets.ISO_8859_1)
        expect(parsedFile.titleText).toEqual("i'm the title")
    }

    @Test
    fun `can read html from file with custom charset and invoke document lambda`() {
        val fileToParse = File("src/jvmTest/resources/__files/example.html")
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

actual fun getInputFromFile(location: String): Input = FileInputStream(location).asInput()
actual fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()