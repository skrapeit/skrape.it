package it.skrape.core

import io.ktor.utils.io.core.*
import org.w3c.files.FileReader

actual fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()

actual fun getInputFromFile(location: String): Input {
    FileReader
    TODO("Not yet implemented")
}