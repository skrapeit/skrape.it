package it.skrape.selects.html5

import it.skrape.core.htmlDocument
import it.skrape.selects.attribute
import it.skrape.selects.text
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class TextSemanticsSelectorsKtTest {

    @Test
    fun `can parse a-tag`() {
        htmlDocument("<div><a>hello</a></div>") {
            a {
                findAll {
                    expectThat(text).isEqualTo("hello")
                }
            }
            div {
                findFirst {
                    a {
                        findFirst {
                            expectThat(text).isEqualTo("hello")
                        }
                    }
                }
                a {
                    findFirst {
                        expectThat(text).isEqualTo("hello")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse abbr-tag`() {
        htmlDocument("<div><abbr>hello</abbr></div>") {
            abbr {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    abbr { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                abbr {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse b-tag`() {
        htmlDocument("<div><b>hello</b></div>") {
            b {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    b { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                b {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse bdi-tag`() {
        htmlDocument("<div><bdi>hello</bdi></div>") {
            bdi {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    bdi { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                bdi {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse bdo-tag`() {
        htmlDocument("<div><bdo>hello</bdo></div>") {
            bdo {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    bdo { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                bdo {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse br-tag`() {
        htmlDocument("<div><br class='foo'/></div>") {
            br {
                findAll {
                    expectThat(attribute("class")).isEqualTo("foo")
                }
            }
            div {
                findFirst {
                    br {
                        findFirst {
                            expectThat(attribute("class")).isEqualTo("foo")
                        }
                    }
                }
                br {
                    findFirst {
                        expectThat(attribute("class")).isEqualTo("foo")
                    }
                }
            }
        }
    }

    @Test
    fun `can parse cite-tag`() {
        htmlDocument("<div><cite>hello</cite></div>") {
            cite {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    cite { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                cite {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse code-tag`() {
        htmlDocument("<div><code>hello</code></div>") {
            code {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    code { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                code {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse data-tag`() {
        htmlDocument("<div><data>hello</data></div>") {
            data {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    data { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                data {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse dfn-tag`() {
        htmlDocument("<div><dfn>hello</dfn></div>") {
            dfn {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    dfn { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                dfn {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse em-tag`() {
        htmlDocument("<div><em>hello</em></div>") {
            em {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    em { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                em {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse i-tag`() {
        htmlDocument("<div><i>hello</i></div>") {
            i {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    i { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                i {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse kbd-tag`() {
        htmlDocument("<div><kbd>hello</kbd></div>") {
            kbd {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    kbd { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                kbd {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse mark-tag`() {
        htmlDocument("<div><mark>hello</mark></div>") {
            mark {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    mark { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                mark {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse q-tag`() {
        htmlDocument("<div><q>hello</q></div>") {
            q {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    q { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                q {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse rb-tag`() {
        htmlDocument("<div><rb>hello</rb></div>") {
            rb {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    rb { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                rb {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse rtc-tag`() {
        htmlDocument("<div><rtc>hello</rtc></div>") {
            rtc {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    rtc { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                rtc {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse ruby-tag`() {
        htmlDocument("<div><ruby>hello</ruby></div>") {
            ruby {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    ruby { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                ruby {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse s-tag`() {
        htmlDocument("<div><s>hello</s></div>") {
            s {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    s { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                s {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse samp-tag`() {
        htmlDocument("<div><samp>hello</samp></div>") {
            samp {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    samp { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                samp {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse small-tag`() {
        htmlDocument("<div><small>hello</small></div>") {
            small {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    small { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                small {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse span-tag`() {
        htmlDocument("<div><span>hello</span></div>") {
            span {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    span { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                span {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse strong-tag`() {
        htmlDocument("<div><strong>hello</strong></div>") {
            strong {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    strong { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                strong {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse sub-tag`() {
        htmlDocument("<div><sub>hello</sub></div>") {
            sub {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    sub { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                sub {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse sup-tag`() {
        htmlDocument("<div><sup>hello</sup></div>") {
            sup {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    sup { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                sup {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse time-tag`() {
        htmlDocument("<div><time>hello</time></div>") {
            time {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    time { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                time {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse tt-tag`() {
        htmlDocument("<div><tt>hello</tt></div>") {
            tt {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    tt { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                tt {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse u-tag`() {
        htmlDocument("<div><u>hello</u></div>") {
            u {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    u { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                u {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse var-tag`() {
        htmlDocument("<div><var>hello</var></div>") {
            `var` {
                findAll { expectThat(text).isEqualTo("hello") }
            }
            div {
                findFirst {
                    `var` { findFirst { expectThat(text).isEqualTo("hello") } }
                }
                `var` {
                    findFirst { expectThat(text).isEqualTo("hello") }
                }
            }
        }
    }

    @Test
    fun `can parse wbr-tag`() {
        htmlDocument("<div><wbr class='foo'/></div>") {
            wbr {
                findAll {
                    expectThat(attribute("class")).isEqualTo("foo")
                }
            }
            div {
                findFirst {
                    wbr {
                        findFirst {
                            expectThat(attribute("class")).isEqualTo("foo")
                        }
                    }
                }
                wbr {
                    findFirst {
                        expectThat(attribute("class")).isEqualTo("foo")
                    }
                }
            }
        }
    }
}
