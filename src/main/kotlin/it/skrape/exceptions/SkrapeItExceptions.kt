package it.skrape.exceptions


open class ElementNotFoundException(selector: String, tag: String = ""):
        Exception("Could not find element \"$tag$selector\"")

class UnsupportedRequestOptionException(message:String): IllegalArgumentException(message)
