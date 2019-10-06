package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.element
import org.jsoup.nodes.Element

/**
 * Will pick the first occurrences of an <html> element from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Element
 */
@SkrapeItDslMarker
fun Result.html(cssSelector: String = "", init: Element.() -> Unit) = element("html$cssSelector", init)
