package it.skrape

import it.skrape.core.htmlDocument
import org.intellij.lang.annotations.Language


@Language("HTML")
fun aValidHtml(bodyInclude: String) = """
    <!DOCTYPE html>
    <html lang="en">
        <head>
            ${aValidTitle()}
            <link rel="shortcut icon" href="https://some.url/icon">
            <script src="https://some.url/some-script.js"></script>
            <noscript id="noscript-css"><style>body,.top-bar{margin-top:1.9em}</style></noscript>
            <meta name="foo" content="bar">
            <style>.foo {color: black}</style>
        </head>
        <body>
            i'm the body
            <header>
                <h1>i'm the headline</h1>
                <nav>
                    <ol class='ordered-navigation'>
                        <li>1st nav item</li>
                        <li>2nd nav item</li>
                        <li>3rd nav item</li>
                        <li>last nav item</li>
                    </ol>
                    <ul class='unordered-navigation'>
                        <li>1st nav item</li>
                        <li>2nd nav item</li>
                        <li>3rd nav item</li>
                        <li>last nav item</li>
                    </ul>
                </nav>
            </header>
            <main>
                <p>i'm a paragraph</p>
                <p>i'm a second paragraph</p>
                <p>i'm a paragraph <wbr> with word break</p>
                <p>i'm the last paragraph</p>
            </main>
            $bodyInclude
            <section>
                <div>
                    <h2>i'm a h2</h2>
                    <h3>i'm a h3</h3>
                    <h4>i'm a h4</h4>
                    <h5>i'm a h5</h5>
                    <h6>i'm a h6</h6>
                    <span>i'm the 1st span</span>
                    <span>i'm the 2nd span</span>
                    <span>i'm the 3rd span</span>
                    <span>i'm the last span</span>
                    <a href='http://some.link'>i'm an anchor</a>
                </div>
            </section>
            <hr />
            <table>
                <caption>i'm the caption</caption>
                <colgroup>
                    <col span="2" style="background-color:red">
                    <col style="background-color:yellow">
                </colgroup>
                <thead>
                    <tr>
                        <th>first th</th>
                        <th>second th</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>foo</td>
                        <td>bar</td>
                    </tr>
                    <tr>
                        <td>barfoo</td>
                        <td>foobar</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td>first foot td</td>
                        <td>second foot td</td>
                    </tr>
                </tfoot>
            </table>
            <hr />
            <dl>
                <dt>Coffee</dt>
                <dd>Black hot drink</dd>
                <dt>Milk</dt>
                <dd>White cold drink</dd>
            </dl>
            <blockquote>i'm a quote</blockquote>
            <dir>
                <li>html</li>
                <li>xhtml</li>
                <li>css</li>
            </dir>
            <figure>
                <figcaption>Fig.1</figcaption>
            </figure>
            <pre>i'm a pre</pre>
            <article>
                <span>i'm an article</span>
            </article>
            <aside>
                <span>i'm an aside</span>
            </aside>
            <footer>
                <address>i'm an address</address>
                <abbr title="some title">i'm an abbr</abbr>
                <b>i'm a bold text</b>
                <br />
                <br>
                <bdi>i'm a bdi</bdi>
                <bdo>i'm a bdo</bdo>
                <cite>i'm a cite</cite>
                <code>i'm a code</code>
                <data>i'm a data</data>
                <dfn>i'm a dfn</dfn>
                <em>i'm a em</em>
                <i>i'm a i</i>
                <kbd>i'm a kbd</kbd>
                <q>i'm a q</q>
                <mark>i'm a mark</mark>
                <rb>i'm a rb</rb>
                <rtc>i'm a rtc</rtc>
                <ruby>
                    i'm a ruby
                    <rt>i'm a rt<rp>i'm a rp</rp></rt>
                </ruby>
                <s>i'm a s</s>
                <samp>i'm a samp</samp>
                <small>i'm a small</small>
                <strong>i'm a strong</strong>
                <sub>i'm a sub</sub>
                <sup>i'm a sup</sup>
                <time>i'm a time</time>
                <tt>i'm a tt</tt>
                <u>i'm a u</u>
                <var>i'm a var</var>
            </footer>
            <a-custom-tag>i'm a custom html5 tag</a-custom-tag>
            <div class='with-children'>
                i'm a parent div
                <div>
                    i'm a child div
                    <div>i'm a grand-child div</div>
                </div>
            </div>
        </body>
    </html>
""".trimIndent()

fun aValidDocument(bodyInclude: String = "") = htmlDocument(aValidHtml(bodyInclude)) {}

fun aSelfClosingTag(tag: String) = "<$tag custom-attr='$tag-attr' />"

fun aStandardTag(tag: String) = """<$tag class="$tag-class">i'm a $tag</$tag>"""

fun aValidTitle(text: String = "i'm the title") = "<title>$text</title>"
