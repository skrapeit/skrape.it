package it.skrape.selects

import it.skrape.SkrapeItDsl
import it.skrape.exceptions.ElementNotFoundException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Suppress("TooManyFunctions")
@SkrapeItDsl
class Doc(val document: Document, var relaxed: Boolean = false): DomTreeElement() {
    override val element: Element
        get() = this.document

    /**
     * Get the (unencoded) text of all children of this element, including any newlines and spaces present in the
     * original.
     *
     * @return unencoded, un-normalized text
     * @see text
     */
    val wholeText by lazy { document.wholeText().orEmpty() }

    val titleText by lazy { document.title().orEmpty() }

    /**
     * Find all elements in the document.
     * @return List<DocElement>
     */
    override val allElements by lazy { document.allElements.map { DocElement(it) } }

    override infix fun findAll(cssSelector: String): List<DocElement> {
        val selected = document.select(cssSelector)
                .map { DocElement(it) }
                .takeIf { it.isNotEmpty() }
        return if (relaxed) selected.orEmpty() else selected ?: throw ElementNotFoundException(cssSelector)
    }

    override infix fun findFirst(cssSelector: String) = findAll(cssSelector).firstOrNull() ?: DocElement(Element(cssSelector))
}
