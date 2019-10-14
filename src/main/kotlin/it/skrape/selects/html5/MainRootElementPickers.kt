package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Doc
import it.skrape.selects.element
import org.jsoup.nodes.Element

/**
 * Will pick the first occurrences of an <html> element from a Doc.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return T
 */
@SkrapeItDslMarker
fun <T> Doc.html(cssSelector: String = "", init: Element.() -> T) = element("html$cssSelector", init)
