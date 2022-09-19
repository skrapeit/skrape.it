package it.skrape.selects.html5

import a3TimesNestedTag
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
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
                    expect(text).toEqual("123 23 3")
                }
                findFirst {
                    expect(text).toEqual("123")
                }
                applet {
                    findAll {
                        expect(text).toEqual("23 3")
                    }
                    findFirst {
                        expect(text).toEqual("23")
                        applet {
                            findAll {
                                expect(text).toEqual("3")
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
                    expect(attribute("src")).toEqual("helloworld.swf")
                }
            }
            div {
                findFirst {
                    embed {
                        findFirst {
                            expect(attribute("src")).toEqual("helloworld.swf")
                        }
                    }
                }
                embed {
                    findFirst {
                        expect(attribute("src")).toEqual("helloworld.swf")
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
                    expect(text).toEqual("hello")
                }
            }
            div {
                findFirst {
                    iframe {
                        findFirst {
                            expect(text).toEqual("hello")
                        }
                    }
                }
                iframe {
                    findFirst {
                        expect(text).toEqual("hello")
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
                    expect(attribute("src")).toEqual("helloworld.swf")
                }
            }
            div {
                findFirst {
                    noembed {
                        findFirst {
                            expect(attribute("src")).toEqual("helloworld.swf")
                        }
                    }
                }
                noembed {
                    findFirst {
                        expect(attribute("src")).toEqual("helloworld.swf")
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
                    expect(text).toEqual("123 23 3")
                }
                findFirst {
                    expect(text).toEqual("123")
                }
                `object` {
                    findAll {
                        expect(text).toEqual("23 3")
                    }
                    findFirst {
                        expect(text).toEqual("23")
                        `object` {
                            findAll {
                                expect(text).toEqual("3")
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
                    expect(attribute("name")).toEqual("autoplay")
                }
            }
            div {
                findFirst {
                    param {
                        findFirst {
                            expect(attribute("name")).toEqual("autoplay")
                        }
                    }
                }
                param {
                    findFirst {
                        expect(attribute("name")).toEqual("autoplay")
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
                    expect(text).toEqual("123 23 3")
                }
                findFirst {
                    expect(text).toEqual("123")
                }
                picture {
                    findAll {
                        expect(text).toEqual("23 3")
                    }
                    findFirst {
                        expect(text).toEqual("23")
                        picture {
                            findAll {
                                expect(text).toEqual("3")
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
                    expect(attribute("src")).toEqual("horse.ogg")
                }
            }
            div {
                findFirst {
                    source {
                        findFirst {
                            expect(attribute("src")).toEqual("horse.ogg")
                        }
                    }
                }
                source {
                    findFirst {
                        expect(attribute("src")).toEqual("horse.ogg")
                    }
                }
            }
        }
    }
}