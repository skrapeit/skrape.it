package it.skrape.exceptions


class ElementNotFoundException(selector: String): Exception("Could not find element \"$selector\"")
