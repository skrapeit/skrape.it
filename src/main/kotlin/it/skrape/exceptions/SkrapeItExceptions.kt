package it.skrape.exceptions


open class ElementNotFoundException(selector: String, tag: String = ""):
        Exception("Could not find element \"$tag$selector\"")

class DivElementNotFoundException(selector: String): ElementNotFoundException(selector, "div")

class MetaElementNotFoundException(selector: String): ElementNotFoundException(selector, "meta")

class SpanElementNotFoundException(selector: String): ElementNotFoundException(selector, "span")

class DocumentNotParsable(documentToParse: String): Exception("Could not parse string \n\"$documentToParse\"")

class UnsupportedRequestOptionException(message:String): IllegalArgumentException(message)
