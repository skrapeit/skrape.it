package it.skrape.selects

public open class ElementNotFoundException(selector: String, tag: String = "") :
    Exception("Could not find element \"$tag$selector\"")
