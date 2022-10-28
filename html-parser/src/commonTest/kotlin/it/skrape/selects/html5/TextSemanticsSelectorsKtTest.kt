package it.skrape.selects.html5

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import it.skrape.core.htmlDocument
import it.skrape.selects.attribute
import it.skrape.selects.text
import kotlin.js.JsName
import kotlin.test.Test
class TextSemanticsSelectorsKtTest {

    @JsName("CanParseATag")
	@Test
	fun `can parse a-tag`() {
        htmlDocument("<div><a>hello</a></div>") {
            a {
                findAll {
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    a {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                a {
                    findFirst {
                        expect(text).toEqual("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseAbbrTag")
	@Test
	fun `can parse abbr-tag`() {
        htmlDocument("<div><abbr>hello</abbr></div>") {
            abbr {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    abbr { findFirst { expect(text).toEqual("hello") } }
                }
                abbr {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseBTag")
	@Test
	fun `can parse b-tag`() {
        htmlDocument("<div><b>hello</b></div>") {
            b {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    b { findFirst { expect(text).toEqual("hello") } }
                }
                b {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseBdiTag")
	@Test
	fun `can parse bdi-tag`() {
        htmlDocument("<div><bdi>hello</bdi></div>") {
            bdi {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    bdi { findFirst { expect(text).toEqual("hello") } }
                }
                bdi {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseBdoTag")
	@Test
	fun `can parse bdo-tag`() {
        htmlDocument("<div><bdo>hello</bdo></div>") {
            bdo {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    bdo { findFirst { expect(text).toEqual("hello") } }
                }
                bdo {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseBrTag")
	@Test
	fun `can parse br-tag`() {
        htmlDocument("<div><br class='foo'/></div>") {
            br {
                findAll {
                    expect(attribute("class")).toEqual("foo")
                }
            }
            div {
                findFirst {
                    br {
                        findFirst {
                            expect(attribute("class")).toEqual("foo")
                        }
                    }
                }
                br {
                    findFirst {
                        expect(attribute("class")).toEqual("foo")
                    }
                }
            }
        }
    }

    @JsName("CanParseCiteTag")
	@Test
	fun `can parse cite-tag`() {
        htmlDocument("<div><cite>hello</cite></div>") {
            cite {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    cite { findFirst { expect(text).toEqual("hello") } }
                }
                cite {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseCodeTag")
	@Test
	fun `can parse code-tag`() {
        htmlDocument("<div><code>hello</code></div>") {
            code {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    code { findFirst { expect(text).toEqual("hello") } }
                }
                code {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseDataTag")
	@Test
	fun `can parse data-tag`() {
        htmlDocument("<div><data>hello</data></div>") {
            data {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    data { findFirst { expect(text).toEqual("hello") } }
                }
                data {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseDfnTag")
	@Test
	fun `can parse dfn-tag`() {
        htmlDocument("<div><dfn>hello</dfn></div>") {
            dfn {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    dfn { findFirst { expect(text).toEqual("hello") } }
                }
                dfn {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseEmTag")
	@Test
	fun `can parse em-tag`() {
        htmlDocument("<div><em>hello</em></div>") {
            em {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    em { findFirst { expect(text).toEqual("hello") } }
                }
                em {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseITag")
	@Test
	fun `can parse i-tag`() {
        htmlDocument("<div><i>hello</i></div>") {
            i {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    i { findFirst { expect(text).toEqual("hello") } }
                }
                i {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseKbdTag")
	@Test
	fun `can parse kbd-tag`() {
        htmlDocument("<div><kbd>hello</kbd></div>") {
            kbd {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    kbd { findFirst { expect(text).toEqual("hello") } }
                }
                kbd {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseMarkTag")
	@Test
	fun `can parse mark-tag`() {
        htmlDocument("<div><mark>hello</mark></div>") {
            mark {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    mark { findFirst { expect(text).toEqual("hello") } }
                }
                mark {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseQTag")
	@Test
	fun `can parse q-tag`() {
        htmlDocument("<div><q>hello</q></div>") {
            q {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    q { findFirst { expect(text).toEqual("hello") } }
                }
                q {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseRbTag")
	@Test
	fun `can parse rb-tag`() {
        htmlDocument("<div><rb>hello</rb></div>") {
            rb {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    rb { findFirst { expect(text).toEqual("hello") } }
                }
                rb {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseRtcTag")
	@Test
	fun `can parse rtc-tag`() {
        htmlDocument("<div><rtc>hello</rtc></div>") {
            rtc {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    rtc { findFirst { expect(text).toEqual("hello") } }
                }
                rtc {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseRubyTag")
	@Test
	fun `can parse ruby-tag`() {
        htmlDocument("<div><ruby>hello</ruby></div>") {
            ruby {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    ruby { findFirst { expect(text).toEqual("hello") } }
                }
                ruby {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSTag")
	@Test
	fun `can parse s-tag`() {
        htmlDocument("<div><s>hello</s></div>") {
            s {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    s { findFirst { expect(text).toEqual("hello") } }
                }
                s {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSampTag")
	@Test
	fun `can parse samp-tag`() {
        htmlDocument("<div><samp>hello</samp></div>") {
            samp {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    samp { findFirst { expect(text).toEqual("hello") } }
                }
                samp {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSmallTag")
	@Test
	fun `can parse small-tag`() {
        htmlDocument("<div><small>hello</small></div>") {
            small {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    small { findFirst { expect(text).toEqual("hello") } }
                }
                small {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSpanTag")
	@Test
	fun `can parse span-tag`() {
        htmlDocument("<div><span>hello</span></div>") {
            span {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    span { findFirst { expect(text).toEqual("hello") } }
                }
                span {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseStrongTag")
	@Test
	fun `can parse strong-tag`() {
        htmlDocument("<div><strong>hello</strong></div>") {
            strong {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    strong { findFirst { expect(text).toEqual("hello") } }
                }
                strong {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSubTag")
	@Test
	fun `can parse sub-tag`() {
        htmlDocument("<div><sub>hello</sub></div>") {
            sub {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    sub { findFirst { expect(text).toEqual("hello") } }
                }
                sub {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseSupTag")
	@Test
	fun `can parse sup-tag`() {
        htmlDocument("<div><sup>hello</sup></div>") {
            sup {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    sup { findFirst { expect(text).toEqual("hello") } }
                }
                sup {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseTimeTag")
	@Test
	fun `can parse time-tag`() {
        htmlDocument("<div><time>hello</time></div>") {
            time {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    time { findFirst { expect(text).toEqual("hello") } }
                }
                time {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseTtTag")
	@Test
	fun `can parse tt-tag`() {
        htmlDocument("<div><tt>hello</tt></div>") {
            tt {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    tt { findFirst { expect(text).toEqual("hello") } }
                }
                tt {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseUTag")
	@Test
	fun `can parse u-tag`() {
        htmlDocument("<div><u>hello</u></div>") {
            u {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    u { findFirst { expect(text).toEqual("hello") } }
                }
                u {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseVarTag")
	@Test
	fun `can parse var-tag`() {
        htmlDocument("<div><var>hello</var></div>") {
            `var` {
                findAll { expect(text).toEqual("hello") }
            }
            div {
                findFirst {
                    `var` { findFirst { expect(text).toEqual("hello") } }
                }
                `var` {
                    findFirst { expect(text).toEqual("hello") }
                }
            }
        }
    }

    @JsName("CanParseWbrTag")
	@Test
	fun `can parse wbr-tag`() {
        htmlDocument("<div><wbr class='foo'/></div>") {
            wbr {
                findAll {
                    expect(attribute("class")).toEqual("foo")
                }
            }
            div {
                findFirst {
                    wbr {
                        findFirst {
                            expect(attribute("class")).toEqual("foo")
                        }
                    }
                }
                wbr {
                    findFirst {
                        expect(attribute("class")).toEqual("foo")
                    }
                }
            }
        }
    }
}