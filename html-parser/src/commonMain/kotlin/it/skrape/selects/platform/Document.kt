package it.skrape.selects.platform

expect class Document : Element {
    constructor(baseUri: String)
    fun title(): String
}