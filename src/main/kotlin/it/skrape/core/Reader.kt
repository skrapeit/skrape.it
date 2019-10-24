package it.skrape.core

import java.io.File
import java.nio.charset.Charset

internal class Reader(
        val file: File,
        val charset: Charset = Charsets.UTF_8
) {

    internal fun read(): Doc = Parser(file.readText(Charsets.UTF_8)).parse()
}
