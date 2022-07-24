package it.skrape.core

import it.skrape.selects.Doc

public expect fun htmlParser(
    html: String,
    baseUri: String,
): Doc
