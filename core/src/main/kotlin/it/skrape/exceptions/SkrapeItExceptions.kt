package it.skrape.exceptions


public open class ElementNotFoundException(selector: String, tag: String = "") :
        Exception("Could not find element \"$tag$selector\"")

public class UnsupportedRequestOptionException(message: String) : IllegalArgumentException(message)
