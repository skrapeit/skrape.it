package it.skrape.selects.html5

import it.skrape.SkrapeItDslMarker
import it.skrape.core.Result
import it.skrape.selects.elements
import org.jsoup.select.Elements

@SkrapeItDslMarker
fun Result.a(cssSelector: String ="", init: Elements.() -> Unit) = elements("a$cssSelector", init)

@SkrapeItDslMarker
fun Result.abbr(cssSelector: String ="", init: Elements.() -> Unit) = elements("abbr$cssSelector", init)

@SkrapeItDslMarker
fun Result.b(cssSelector: String ="", init: Elements.() -> Unit) = elements("b$cssSelector", init)

@SkrapeItDslMarker
fun Result.bdi(cssSelector: String ="", init: Elements.() -> Unit) = elements("bdi$cssSelector", init)

@SkrapeItDslMarker
fun Result.bdo(cssSelector: String ="", init: Elements.() -> Unit) = elements("bdo$cssSelector", init)

@SkrapeItDslMarker
fun Result.br(cssSelector: String ="", init: Elements.() -> Unit) = elements("br$cssSelector", init)

@SkrapeItDslMarker
fun Result.cite(cssSelector: String ="", init: Elements.() -> Unit) = elements("cite$cssSelector", init)

@SkrapeItDslMarker
fun Result.code(cssSelector: String ="", init: Elements.() -> Unit) = elements("code$cssSelector", init)

@SkrapeItDslMarker
fun Result.data(cssSelector: String ="", init: Elements.() -> Unit) = elements("data$cssSelector", init)

@SkrapeItDslMarker
fun Result.dfn(cssSelector: String ="", init: Elements.() -> Unit) = elements("dfn$cssSelector", init)

@SkrapeItDslMarker
fun Result.em(cssSelector: String ="", init: Elements.() -> Unit) = elements("em$cssSelector", init)

@SkrapeItDslMarker
fun Result.i(cssSelector: String ="", init: Elements.() -> Unit) = elements("i$cssSelector", init)

@SkrapeItDslMarker
fun Result.kbd(cssSelector: String ="", init: Elements.() -> Unit) = elements("kbd$cssSelector", init)

@SkrapeItDslMarker
fun Result.mark(cssSelector: String ="", init: Elements.() -> Unit) = elements("mark$cssSelector", init)

@SkrapeItDslMarker
fun Result.q(cssSelector: String ="", init: Elements.() -> Unit) = elements("q$cssSelector", init)

@SkrapeItDslMarker
fun Result.rb(cssSelector: String ="", init: Elements.() -> Unit) = elements("rb$cssSelector", init)

@SkrapeItDslMarker
fun Result.rp(cssSelector: String ="", init: Elements.() -> Unit) = elements("rp$cssSelector", init)

@SkrapeItDslMarker
fun Result.rt(cssSelector: String ="", init: Elements.() -> Unit) = elements("rt$cssSelector", init)

@SkrapeItDslMarker
fun Result.rtc(cssSelector: String ="", init: Elements.() -> Unit) = elements("rtc$cssSelector", init)

@SkrapeItDslMarker
fun Result.ruby(cssSelector: String ="", init: Elements.() -> Unit) = elements("ruby$cssSelector", init)

@SkrapeItDslMarker
fun Result.s(cssSelector: String ="", init: Elements.() -> Unit) = elements("s$cssSelector", init)

@SkrapeItDslMarker
fun Result.samp(cssSelector: String ="", init: Elements.() -> Unit) = elements("samp$cssSelector", init)

@SkrapeItDslMarker
fun Result.small(cssSelector: String ="", init: Elements.() -> Unit) = elements("small$cssSelector", init)

@SkrapeItDslMarker
fun Result.span(cssSelector: String ="", init: Elements.() -> Unit) = elements("span$cssSelector", init)

@SkrapeItDslMarker
fun Result.strong(cssSelector: String ="", init: Elements.() -> Unit) = elements("strong$cssSelector", init)

@SkrapeItDslMarker
fun Result.sub(cssSelector: String ="", init: Elements.() -> Unit) = elements("sub$cssSelector", init)

@SkrapeItDslMarker
fun Result.sup(cssSelector: String ="", init: Elements.() -> Unit) = elements("sup$cssSelector", init)

@SkrapeItDslMarker
fun Result.time(cssSelector: String ="", init: Elements.() -> Unit) = elements("time$cssSelector", init)

@SkrapeItDslMarker
fun Result.tt(cssSelector: String ="", init: Elements.() -> Unit) = elements("tt$cssSelector", init)

@SkrapeItDslMarker
fun Result.u(cssSelector: String ="", init: Elements.() -> Unit) = elements("u$cssSelector", init)

@SkrapeItDslMarker
fun Result.`var`(cssSelector: String ="", init: Elements.() -> Unit) = elements("var$cssSelector", init)

@SkrapeItDslMarker
fun Result.wbr(cssSelector: String ="", init: Elements.() -> Unit) = elements("wbr$cssSelector", init)
