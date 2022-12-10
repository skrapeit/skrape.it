package it.skrape.selects.html5


import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.attribute
import it.skrape.selects.text

class TextSemanticsSelectorsKtTest: FunSpec({

    test("can parse a-tag") {
        htmlDocument("<div><a>hello</a></div>") {
            a {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    a {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                a {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    test("can parse abbr-tag") {
        htmlDocument("<div><abbr>hello</abbr></div>") {
            abbr {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    abbr { findFirst { text.shouldBe("hello") } }
                }
                abbr {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse b-tag") {
        htmlDocument("<div><b>hello</b></div>") {
            b {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    b { findFirst { text.shouldBe("hello") } }
                }
                b {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse bdi-tag") {
        htmlDocument("<div><bdi>hello</bdi></div>") {
            bdi {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    bdi { findFirst { text.shouldBe("hello") } }
                }
                bdi {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse bdo-tag") {
        htmlDocument("<div><bdo>hello</bdo></div>") {
            bdo {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    bdo { findFirst { text.shouldBe("hello") } }
                }
                bdo {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse br-tag") {
        htmlDocument("<div><br class='foo'/></div>") {
            br {
                findAll {
                    attribute("class").shouldBe("foo")
                }
            }
            div {
                findFirst {
                    br {
                        findFirst {
                            attribute("class").shouldBe("foo")
                        }
                    }
                }
                br {
                    findFirst {
                        attribute("class").shouldBe("foo")
                    }
                }
            }
        }
    }

    test("can parse cite-tag") {
        htmlDocument("<div><cite>hello</cite></div>") {
            cite {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    cite { findFirst { text.shouldBe("hello") } }
                }
                cite {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse code-tag") {
        htmlDocument("<div><code>hello</code></div>") {
            code {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    code { findFirst { text.shouldBe("hello") } }
                }
                code {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse data-tag") {
        htmlDocument("<div><data>hello</data></div>") {
            data {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    data { findFirst { text.shouldBe("hello") } }
                }
                data {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse dfn-tag") {
        htmlDocument("<div><dfn>hello</dfn></div>") {
            dfn {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    dfn { findFirst { text.shouldBe("hello") } }
                }
                dfn {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse em-tag") {
        htmlDocument("<div><em>hello</em></div>") {
            em {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    em { findFirst { text.shouldBe("hello") } }
                }
                em {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse i-tag") {
        htmlDocument("<div><i>hello</i></div>") {
            i {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    i { findFirst { text.shouldBe("hello") } }
                }
                i {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse kbd-tag") {
        htmlDocument("<div><kbd>hello</kbd></div>") {
            kbd {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    kbd { findFirst { text.shouldBe("hello") } }
                }
                kbd {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse mark-tag") {
        htmlDocument("<div><mark>hello</mark></div>") {
            mark {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    mark { findFirst { text.shouldBe("hello") } }
                }
                mark {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse q-tag") {
        htmlDocument("<div><q>hello</q></div>") {
            q {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    q { findFirst { text.shouldBe("hello") } }
                }
                q {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse rb-tag") {
        htmlDocument("<div><rb>hello</rb></div>") {
            rb {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    rb { findFirst { text.shouldBe("hello") } }
                }
                rb {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse rtc-tag") {
        htmlDocument("<div><rtc>hello</rtc></div>") {
            rtc {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    rtc { findFirst { text.shouldBe("hello") } }
                }
                rtc {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse ruby-tag") {
        htmlDocument("<div><ruby>hello</ruby></div>") {
            ruby {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    ruby { findFirst { text.shouldBe("hello") } }
                }
                ruby {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse s-tag") {
        htmlDocument("<div><s>hello</s></div>") {
            s {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    s { findFirst { text.shouldBe("hello") } }
                }
                s {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse samp-tag") {
        htmlDocument("<div><samp>hello</samp></div>") {
            samp {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    samp { findFirst { text.shouldBe("hello") } }
                }
                samp {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse small-tag") {
        htmlDocument("<div><small>hello</small></div>") {
            small {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    small { findFirst { text.shouldBe("hello") } }
                }
                small {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse span-tag") {
        htmlDocument("<div><span>hello</span></div>") {
            span {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    span { findFirst { text.shouldBe("hello") } }
                }
                span {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse strong-tag") {
        htmlDocument("<div><strong>hello</strong></div>") {
            strong {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    strong { findFirst { text.shouldBe("hello") } }
                }
                strong {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse sub-tag") {
        htmlDocument("<div><sub>hello</sub></div>") {
            sub {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    sub { findFirst { text.shouldBe("hello") } }
                }
                sub {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse sup-tag") {
        htmlDocument("<div><sup>hello</sup></div>") {
            sup {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    sup { findFirst { text.shouldBe("hello") } }
                }
                sup {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse time-tag") {
        htmlDocument("<div><time>hello</time></div>") {
            time {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    time { findFirst { text.shouldBe("hello") } }
                }
                time {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse tt-tag") {
        htmlDocument("<div><tt>hello</tt></div>") {
            tt {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    tt { findFirst { text.shouldBe("hello") } }
                }
                tt {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse u-tag") {
        htmlDocument("<div><u>hello</u></div>") {
            u {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    u { findFirst { text.shouldBe("hello") } }
                }
                u {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse var-tag") {
        htmlDocument("<div><var>hello</var></div>") {
            `var` {
                findAll { text.shouldBe("hello") }
            }
            div {
                findFirst {
                    `var` { findFirst { text.shouldBe("hello") } }
                }
                `var` {
                    findFirst { text.shouldBe("hello") }
                }
            }
        }
    }

    test("can parse wbr-tag") {
        htmlDocument("<div><wbr class='foo'/></div>") {
            wbr {
                findAll {
                    attribute("class").shouldBe("foo")
                }
            }
            div {
                findFirst {
                    wbr {
                        findFirst {
                            attribute("class").shouldBe("foo")
                        }
                    }
                }
                wbr {
                    findFirst {
                        attribute("class").shouldBe("foo")
                    }
                }
            }
        }
    }
})