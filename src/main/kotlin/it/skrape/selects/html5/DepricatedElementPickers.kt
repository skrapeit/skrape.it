package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

/**
 * Will pick all occurrences of <acronym> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.acronym(cssSelector: String ="", init: Elements.() -> Unit) = elements("acronym$cssSelector", init)

/**
 * Will pick all occurrences of <basefont> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.basefont(cssSelector: String ="", init: Elements.() -> Unit) = elements("basefont$cssSelector", init)

/**
 * Will pick all occurrences of <bgsound> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.bgsound(cssSelector: String ="", init: Elements.() -> Unit) = elements("bgsound$cssSelector", init)

/**
 * Will pick all occurrences of <big> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.big(cssSelector: String ="", init: Elements.() -> Unit) = elements("big$cssSelector", init)

/**
 * Will pick all occurrences of <blink> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.blink(cssSelector: String ="", init: Elements.() -> Unit) = elements("blink$cssSelector", init)

/**
 * Will pick all occurrences of <center> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.center(cssSelector: String ="", init: Elements.() -> Unit) = elements("center$cssSelector", init)

/**
 * Will pick all occurrences of <command> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.command(cssSelector: String ="", init: Elements.() -> Unit) = elements("command$cssSelector", init)

/**
 * Will pick all occurrences of <font> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.font(cssSelector: String ="", init: Elements.() -> Unit) = elements("font$cssSelector", init)

/**
 * Will pick all occurrences of <frameset> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.frameset(cssSelector: String ="", init: Elements.() -> Unit) = elements("frameset$cssSelector", init)

/**
 * Will pick all occurrences of <keygen> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.keygen(cssSelector: String ="", init: Elements.() -> Unit) = elements("keygen$cssSelector", init)

/**
 * Will pick all occurrences of <listing> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.listing(cssSelector: String ="", init: Elements.() -> Unit) = elements("listing$cssSelector", init)

/**
 * Will pick all occurrences of <marquee> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.marquee(cssSelector: String ="", init: Elements.() -> Unit) = elements("marquee$cssSelector", init)

/**
 * Will pick all occurrences of <multicol> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.multicol(cssSelector: String ="", init: Elements.() -> Unit) = elements("multicol$cssSelector", init)

/**
 * Will pick all occurrences of <nextid> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.nextid(cssSelector: String ="", init: Elements.() -> Unit) = elements("nextid$cssSelector", init)

/**
 * Will pick all occurrences of <nobr> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.nobr(cssSelector: String ="", init: Elements.() -> Unit) = elements("nobr$cssSelector", init)

/**
 * Will pick all occurrences of <noframes> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.noframes(cssSelector: String ="", init: Elements.() -> Unit) = elements("noframes$cssSelector", init)

/**
 * Will pick all occurrences of <plaintext> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.plaintext(cssSelector: String ="", init: Elements.() -> Unit) = elements("plaintext$cssSelector", init)

/**
 * Will pick all occurrences of <spacer> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.spacer(cssSelector: String ="", init: Elements.() -> Unit) = elements("spacer$cssSelector", init)

/**
 * Will pick all occurrences of <strike> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.strike(cssSelector: String ="", init: Elements.() -> Unit) = elements("strike$cssSelector", init)

/**
 * Will pick all occurrences of <xmp> elements from a Result.
 * The selection can be specified/limited by an additional css-selector.
 * @param cssSelector
 * @return Elements
 */
@SkrapeItDslMarker
fun Result.xmp(cssSelector: String ="", init: Elements.() -> Unit) = elements("xmp$cssSelector", init)
