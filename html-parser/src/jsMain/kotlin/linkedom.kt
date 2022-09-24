@file:JsModule("linkedom/cached")
@file:JsNonModule

external class DOMParser: org.w3c.dom.parsing.DOMParser

external class Node: org.w3c.dom.Node {
    companion object {
        val ELEMENT_NODE: Short
        val TEXT_NODE: Short
        val DOCUMENT_NODE: Short
        val DOCUMENT_FRAGMENT_NODE: Short
        val DOCUMENT_TYPE_NODE: Short
    }
}

external class Document: org.w3c.dom.Document

external fun parseHTML(html: String): JSDOM