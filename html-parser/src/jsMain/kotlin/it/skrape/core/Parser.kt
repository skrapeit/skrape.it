package it.skrape.core

import ConstructorOptions
import JSDOM
import io.ktor.utils.io.charsets.*
import it.skrape.selects.Doc
import it.skrape.selects.platform.Document
import org.w3c.dom.Window
import org.w3c.dom.asList
import org.w3c.dom.parsing.DOMParser
import parseHTML

internal actual class Parser actual constructor(
    actual var html: String,
    actual val charset: Charset,
    actual val jsExecution: Boolean,
    actual val baseUri: String
) {
    companion object {
        public val IS_BROWSER: Boolean = js(
            "typeof window !== 'undefined' && typeof window.document !== 'undefined' || typeof self !== 'undefined' && typeof self.location !== 'undefined'" // ktlint-disable max-line-length
        ) as Boolean

        public val IS_NODE: Boolean = js(
            "typeof process !== 'undefined' && process.versions != null && process.versions.node != null"
        ) as Boolean
    }

    actual fun parse(): Doc {
        val doc = if (jsExecution) {
            println("Using JSDOM")
            JSDOM(html, ConstructorOptions(
                runScripts = if (jsExecution) "dangerously" else undefined,
                resources = if (jsExecution) "usable" else undefined,
                url = baseUri.ifBlank { undefined }
            )).window.document
        } else if (IS_NODE) {
            println("Using linkedom")
            var nDoc = parseHTML(html).window.document
            if (nDoc.childNodes.asList().none { it.nodeType == 10.toShort() }) { //We need to fix this
                println("Fixing doc for ${nDoc.documentElement!!.tagName} (${nDoc.documentElement!!.nodeType})")
                val nnDoc = parseHTML("<html><head></head><body></body></html>").window.document
                for (c in nDoc.childNodes.asList()) {
                    //nDoc.removeChild(c)
                    nnDoc.body!!.appendChild(c)
                }
                nDoc = nnDoc
            }
            nDoc
        } else if (IS_BROWSER) {
            println("Using builtin")
            DOMParser().parseFromString(html, "text/html")
        } else {
            error("Unsupported platform!")
        }
        return Doc(Document(doc))
    }

}

fun ConstructorOptions(
    url: String? = undefined,
    contentType: String? = undefined,
    storageOptions: Number? = undefined,
    referer: String? = undefined,
    userAgent: String? = undefined,
    includeNodeLocations: Boolean? = undefined,
    runScripts: String? = undefined,
    resources: dynamic = undefined,
    virtualConsole: dynamic = undefined,
    cookieJar: dynamic = undefined,
    pretendToBeVisual: Boolean? = undefined,
    beforeParse: (Window) -> Unit = {},
): ConstructorOptions {
    val o = js("({})")
    o["url"] = url
    o["contentType"] = contentType
    o["storageOptions"] = storageOptions
    o["referer"] = referer
    o["userAgent"] = userAgent
    o["includeNodeLocations"] = includeNodeLocations
    o["runScripts"] = runScripts
    o["resources"] = resources
    o["virtualConsole"] = virtualConsole
    o["cookieJar"] = cookieJar
    o["pretendToBeVisual"] = pretendToBeVisual
    o["beforeParse"] = beforeParse
    return o
}