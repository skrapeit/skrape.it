package it.skrape.selects.html5

import a3TimesNestedTag
import io.kotest.matchers.shouldBe
import it.skrape.core.htmlDocument
import it.skrape.selects.text
import kotlin.js.JsName
import kotlin.test.Test

class EmbeddedContentSelectorsKtTest {

    @JsName("CanParseAppletTag")
	@Test
	fun `can parse applet-tag`() {
        htmlDocument(a3TimesNestedTag("applet")) {
            applet {
                findAll {
                    text.shouldBe("123 23 3")
                }
                findFirst {
                    text.shouldBe("123")
                }
                applet {
                    findAll {
                        text.shouldBe("23 3")
                    }
                    findFirst {
                        text.shouldBe("23")
                        applet {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseEmbedTag")
	@Test
	fun `can parse embed-tag`() {
        htmlDocument("<div><embed src='helloworld.swf'></div>") {
            embed {
                findFirst {
                    attribute("src").shouldBe("helloworld.swf")
                }
            }
            div {
                findFirst {
                    embed {
                        findFirst {
                            attribute("src").shouldBe("helloworld.swf")
                        }
                    }
                }
                embed {
                    findFirst {
                        attribute("src").shouldBe("helloworld.swf")
                    }
                }
            }
        }
    }

    @JsName("CanParseIframeTag")
	@Test
	fun `can parse iframe-tag`() {
        htmlDocument("<div><iframe>hello</iframe></div>") {
            iframe {
                findAll {
                    text.shouldBe("hello")
                }
            }
            div {
                findFirst {
                    iframe {
                        findFirst {
                            text.shouldBe("hello")
                        }
                    }
                }
                iframe {
                    findFirst {
                        text.shouldBe("hello")
                    }
                }
            }
        }
    }

    @JsName("CanParseNoembedTag")
	@Test
	fun `can parse noembed-tag`() {
        htmlDocument("<div><noembed src='helloworld.swf'></div>") {
            noembed {
                findFirst {
                    attribute("src").shouldBe("helloworld.swf")
                }
            }
            div {
                findFirst {
                    noembed {
                        findFirst {
                            attribute("src").shouldBe("helloworld.swf")
                        }
                    }
                }
                noembed {
                    findFirst {
                        attribute("src").shouldBe("helloworld.swf")
                    }
                }
            }
        }
    }

    @JsName("CanParseObjectTag")
	@Test
	fun `can parse object-tag`() {
        htmlDocument(a3TimesNestedTag("object")) {
            `object` {
                findAll {
                    text.shouldBe("123 23 3")
                }
                findFirst {
                    text.shouldBe("123")
                }
                `object` {
                    findAll {
                        text.shouldBe("23 3")
                    }
                    findFirst {
                        text.shouldBe("23")
                        `object` {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseParamTag")
	@Test
	fun `can parse param-tag`() {
        htmlDocument("<div><param name='autoplay' value='true'></div>") {
            param {
                findFirst {
                    attribute("name").shouldBe("autoplay")
                }
            }
            div {
                findFirst {
                    param {
                        findFirst {
                            attribute("name").shouldBe("autoplay")
                        }
                    }
                }
                param {
                    findFirst {
                        attribute("name").shouldBe("autoplay")
                    }
                }
            }
        }
    }

    @JsName("CanParsePictureTag")
	@Test
	fun `can parse picture-tag`() {
        htmlDocument(a3TimesNestedTag("picture")) {
            picture {
                findAll {
                    text.shouldBe("123 23 3")
                }
                findFirst {
                    text.shouldBe("123")
                }
                picture {
                    findAll {
                        text.shouldBe("23 3")
                    }
                    findFirst {
                        text.shouldBe("23")
                        picture {
                            findAll {
                                text.shouldBe("3")
                            }
                        }
                    }
                }
            }
        }
    }

    @JsName("CanParseSourceTag")
	@Test
	fun `can parse source-tag`() {
        htmlDocument("<div><source src='horse.ogg' type='audio/ogg'></div>") {
            source {
                findFirst {
                    attribute("src").shouldBe("horse.ogg")
                }
            }
            div {
                findFirst {
                    source {
                        findFirst {
                            attribute("src").shouldBe("horse.ogg")
                        }
                    }
                }
                source {
                    findFirst {
                        attribute("src").shouldBe("horse.ogg")
                    }
                }
            }
        }
    }
}