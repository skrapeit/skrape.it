package it.skrape.selects.html5

import it.skrape.core.htmlDocument
import it.skrape.matchers.toBe
import it.skrape.selects.attribute
import it.skrape.selects.text
import org.junit.jupiter.api.Test


internal class TextSemanticsSelectorsKtTest {

    @Test
    fun `can parse a-tag`() {
        htmlDocument("<div><a>hello</a></div>") {
            a {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    a { findFirst { text toBe "hello" } }
                }
                a {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse abbr-tag`() {
        htmlDocument("<div><abbr>hello</abbr></div>") {
            abbr {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    abbr { findFirst { text toBe "hello" } }
                }
                abbr {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse b-tag`() {
        htmlDocument("<div><b>hello</b></div>") {
            b {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    b { findFirst { text toBe "hello" } }
                }
                b {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse bdi-tag`() {
        htmlDocument("<div><bdi>hello</bdi></div>") {
            bdi {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    bdi { findFirst { text toBe "hello" } }
                }
                bdi {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse bdo-tag`() {
        htmlDocument("<div><bdo>hello</bdo></div>") {
            bdo {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    bdo { findFirst { text toBe "hello" } }
                }
                bdo {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse br-tag`() {
        htmlDocument("<div><br class='foo'/></div>") {
            br {
                findAll { attribute("class") toBe "foo" }
            }
            div {
                findFirst {
                    br { findFirst {attribute("class") toBe "foo" } }
                }
                br {
                    findFirst { attribute("class") toBe "foo" }
                }
            }
        }
    }

    @Test
    fun `can parse cite-tag`() {
        htmlDocument("<div><cite>hello</cite></div>") {
            cite {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    cite { findFirst { text toBe "hello" } }
                }
                cite {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse code-tag`() {
        htmlDocument("<div><code>hello</code></div>") {
            code {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    code { findFirst { text toBe "hello" } }
                }
                code {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse data-tag`() {
        htmlDocument("<div><data>hello</data></div>") {
            data {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    data { findFirst { text toBe "hello" } }
                }
                data {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse dfn-tag`() {
        htmlDocument("<div><dfn>hello</dfn></div>") {
            dfn {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    dfn { findFirst { text toBe "hello" } }
                }
                dfn {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse em-tag`() {
        htmlDocument("<div><em>hello</em></div>") {
            em {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    em { findFirst { text toBe "hello" } }
                }
                em {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse i-tag`() {
        htmlDocument("<div><i>hello</i></div>") {
            i {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    i { findFirst { text toBe "hello" } }
                }
                i {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse kbd-tag`() {
        htmlDocument("<div><kbd>hello</kbd></div>") {
            kbd {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    kbd { findFirst { text toBe "hello" } }
                }
                kbd {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse mark-tag`() {
        htmlDocument("<div><mark>hello</mark></div>") {
            mark {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    mark { findFirst { text toBe "hello" } }
                }
                mark {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse q-tag`() {
        htmlDocument("<div><q>hello</q></div>") {
            q {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    q { findFirst { text toBe "hello" } }
                }
                q {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse rb-tag`() {
        htmlDocument("<div><rb>hello</rb></div>") {
            rb {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    rb { findFirst { text toBe "hello" } }
                }
                rb {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse rtc-tag`() {
        htmlDocument("<div><rtc>hello</rtc></div>") {
            rtc {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    rtc { findFirst { text toBe "hello" } }
                }
                rtc {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse ruby-tag`() {
        htmlDocument("<div><ruby>hello</ruby></div>") {
            ruby {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    ruby { findFirst { text toBe "hello" } }
                }
                ruby {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse s-tag`() {
        htmlDocument("<div><s>hello</s></div>") {
            s {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    s { findFirst { text toBe "hello" } }
                }
                s {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse samp-tag`() {
        htmlDocument("<div><samp>hello</samp></div>") {
            samp {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    samp { findFirst { text toBe "hello" } }
                }
                samp {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse small-tag`() {
        htmlDocument("<div><small>hello</small></div>") {
            small {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    small { findFirst { text toBe "hello" } }
                }
                small {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse span-tag`() {
        htmlDocument("<div><span>hello</span></div>") {
            span {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    span { findFirst { text toBe "hello" } }
                }
                span {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse strong-tag`() {
        htmlDocument("<div><strong>hello</strong></div>") {
            strong {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    strong { findFirst { text toBe "hello" } }
                }
                strong {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse sub-tag`() {
        htmlDocument("<div><sub>hello</sub></div>") {
            sub {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    sub { findFirst { text toBe "hello" } }
                }
                sub {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse sup-tag`() {
        htmlDocument("<div><sup>hello</sup></div>") {
            sup {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    sup { findFirst { text toBe "hello" } }
                }
                sup {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse time-tag`() {
        htmlDocument("<div><time>hello</time></div>") {
            time {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    time { findFirst { text toBe "hello" } }
                }
                time {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse tt-tag`() {
        htmlDocument("<div><tt>hello</tt></div>") {
            tt {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    tt { findFirst { text toBe "hello" } }
                }
                tt {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse u-tag`() {
        htmlDocument("<div><u>hello</u></div>") {
            u {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    u { findFirst { text toBe "hello" } }
                }
                u {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse var-tag`() {
        htmlDocument("<div><var>hello</var></div>") {
            `var` {
                findAll { text toBe "hello" }
            }
            div {
                findFirst {
                    `var` { findFirst { text toBe "hello" } }
                }
                `var` {
                    findFirst { text toBe "hello" }
                }
            }
        }
    }

    @Test
    fun `can parse wbr-tag`() {
        htmlDocument("<div><wbr class='foo'/></div>") {
            wbr {
                findAll { attribute("class") toBe "foo" }
            }
            div {
                findFirst {
                    wbr { findFirst {attribute("class") toBe "foo" } }
                }
                wbr {
                    findFirst { attribute("class") toBe "foo" }
                }
            }
        }
    }
}