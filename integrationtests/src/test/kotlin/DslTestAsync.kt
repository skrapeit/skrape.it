import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.matchers.*
import it.skrape.matchers.ContentTypes.*
import it.skrape.selects.*
import it.skrape.selects.html5.*
import kotlinx.coroutines.runBlocking
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*
import java.io.File
import java.net.SocketTimeoutException

private val wiremock = Testcontainer.wiremock

@Execution(ExecutionMode.SAME_THREAD)
class DslTestAsync {

    @Test
    fun `dsl can skrape by url`() {
        wiremock.setupStub(path = "/example")
        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/example"
            }

            runBlocking {
                expect {

                    status {
                        code toBe 200
                        message toBe "OK"
                    }

                    contentType toBe TEXT_HTML_UTF8

                    htmlDocument {

                        title {
                            findFirst {
                                text toBe "i'm the title"
                            }
                        }

                        p {
                            findAll {
                                toBePresentTimes(2)
                            }
                            findFirst {
                                attribute("data-foo") toBe "bar"
                                text toBe "i'm a paragraph"
                            }

                            findLast {
                                text toBe "i'm a second paragraph"
                            }
                        }
                    }
                }
            }
        }

    }

    @Test
    fun `can call https with relaxed ssl option via DSL`() {
        wiremock.setupStub(path = "/example")

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpsUrl}/example"
                sslRelaxed = true
            }

            runBlocking {
                expect {
                    status { code toBe 200 }
                }
            }
        }

    }

    @Test
    fun `dsl can assert content-type in highly readable way`() {
        wiremock.setupStub(path = "/example")

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/example"
            }

            runBlocking {
                expect {
                    contentType toContain TEXT_HTML
                    contentType toBe TEXT_HTML_UTF8
                    contentType toBeNot APPLICATION_XHTML
                    contentType toBeNot APPLICATION_GZIP
                    contentType toBeNot APPLICATION_JSON
                    contentType toBeNot APPLICATION_TAR
                    contentType toBeNot APPLICATION_XML
                    contentType toBeNot APPLICATION_XUL
                    contentType toBeNot APPLICATION_ZIP

                    expectThat(contentType).isEqualTo("text/html;charset=utf-8")
                }
            }
        }
    }

    @Test
    fun `dsl will not follow redirects if configured`() {
        wiremock.setupRedirect()

        skrape(KtorFetcher) {
            request {
                followRedirects = false
                url = "${wiremock.httpUrl}/"
            }

            runBlocking {
                expect {
                    status {
                        code toBe 302
                        message toBe "Found"
                    }
                }
            }
        }
    }

    @Test
    fun `dsl can check certain header`() {
        wiremock.setupStub()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                expect {
                    val header = httpHeader("Content-Type") {
                        expectThat(this).isEqualTo("text/html;charset=utf-8")
                    }
                    expectThat(header).isEqualTo("text/html;charset=utf-8")

                    val nonExistingHeader = httpHeader("Non-Existing") {
                        expectThat(this).isNull()
                    }
                    expectThat(nonExistingHeader).isNull()
                }
            }
        }
    }

    @Test
    fun `dsl can check headers`() {
        wiremock.setupStub()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                expect {
                    val headers = httpHeaders {
                        expectThat(this).hasEntry("Content-Type", "text/html;charset=utf-8")
                    }
                    expectThat(headers).hasEntry("Content-Type", "text/html;charset=utf-8")
                }
            }
        }
    }

    @Test
    fun `dsl can get body`() {
        wiremock.setupStub()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                expect {
                    htmlDocument {
                        body {
                            findFirst {
                                text toContain "i'm a paragraph"
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `dsl will follow redirect by default`() {
        wiremock.setupRedirect()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                expect {
                    status {
                        code toBe 404
                        message toBe "Not Found"
                    }
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and use HTTP verb POST`() {
        wiremock.setupPostStub()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
                method = Method.POST
            }
            runBlocking {
                expect {

                    //expectThat(request.method).isEqualTo(Method.POST)

                    status {
                        code toBe 200
                        message toBe "OK"
                    }

                    responseStatus toBe HttpStatus.`2xx_Successful`
                    responseStatus toBe HttpStatus.`200_OK`
                    responseStatus toBeNot HttpStatus.`1xx_Informational_response`
                    responseStatus toBeNot HttpStatus.`3xx_Redirection`
                    responseStatus toBeNot HttpStatus.`4xx_Client_error`
                    responseStatus toBeNot HttpStatus.`5xx_Server_error`

                    contentType toBe APPLICATION_JSON_UTF8
                    contentType toBe "application/json;charset=utf-8"
                }
            }
        }
    }

    @Test
    fun `dsl will throw exception on timeout`() {
        wiremock.setupStub(delay = 3000)

        expectThrows<SocketTimeoutException> {
            skrape(KtorFetcher) {
                request {
                    url = "${wiremock.httpUrl}/"
                    timeout = 2000
                }
                runBlocking {
                    expect {}
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and extract to inferred type`() {
        wiremock.setupStub()

        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }

            val extracted = runBlocking {
                extract {
                    status {
                        MyObject(message, "", emptyList())
                    }
                }
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    @Test
    fun `dsl can fetch url and extract using it`() {
        wiremock.setupStub()

        val extracted = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }

            runBlocking {
                extractIt<MyOtherObject> {
                    it.message = status { message }
                }
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
    }

    @Test
    fun `dsl can fetch url and extract using it in DSL-ish fashion`() {
        wiremock.setupStub()

        val extracted = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }

            runBlocking {
                extractIt<MyObject> {
                    it.message = status { message }
                    htmlDocument {
                        it.allParagraphs = p { findAll { eachText } }
                        it.paragraph = findFirst("p").text
                        it.allLinks = findAll("[href]").eachHref
                    }
                }
            }
        }
        expect {
            that(extracted.message).isEqualTo("OK")
            that(extracted.paragraph).isEqualTo("i'm a paragraph")
            that(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
            that(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    /**
     * TODO: fix Class should have a single no-arg constructor: class MyDataClass
     * for classes or data classes that have none default values
     */
    @Test
    fun `dsl can fetch url and extract to data class`() {
        wiremock.setupStub()

        val extracted = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }

            runBlocking {
                extractIt<MyDataClass> {
                    it.httpStatusCode = status { code }
                    it.httpStatusMessage = status { message }
                    htmlDocument {
                        it.allParagraphs = p { findAll { eachText } }
                        it.paragraph = p { findFirst { text } }
                        it.allLinks = a {
                            findAll { eachHref }
                        }
                    }
                }
            }
        }

        expect {
            that(extracted.httpStatusCode).isEqualTo(200)
            that(extracted.httpStatusMessage).isEqualTo("OK")
            that(extracted.paragraph).isEqualTo("i'm a paragraph")
            that(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
            that(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    @Test
    fun `will throw custom exception if element could not be found via lambda`() {

        expectThrows<ElementNotFoundException> {
            skrape(KtorFetcher) {
                request {
                    url = "${wiremock.httpUrl}/"
                }
               runBlocking {
                   expect {
                       htmlDocument {
                           findFirst(".nonExistent") {}
                       }
                   }
               }
            }
        }
    }


    @Test
    fun `will return empty list if element could not be found and Doc mode is relaxed`() {


        skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                expect {
                    htmlDocument {
                        relaxed = true
                        findAll(".nonExistent") {
                            toBeEmpty
                        }
                        findFirst(".nonExistent") {
                            // not throwing Exception
                        }
                    }
                }
            }
        }

    }

    @Test
    fun `will throw custom exception if element called by dsl could not be found`() {

        expectThrows<ElementNotFoundException> {
            skrape(KtorFetcher) {
                request {
                    url = "${wiremock.httpUrl}/"
                }
                runBlocking {
                    expect {
                        htmlDocument {
                            div {
                                withId = "non-existend"
                                withClass = "non-existend"
                                withAttribute = "non" to "existend"
                                withAttributeKey = "non-existend"
                                withAttributes = listOf("non" to "existend")
                                withAttributeKeys = listOf("non-existend")
                                findFirst {}
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and extract from skrape`() {
        wiremock.setupStub()

        val extracted = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }

            runBlocking {
                extract {
                    htmlDocument {
                        MyObject(
                            message = "",
                            allParagraphs = p { findAll { eachText } },
                            paragraph = findFirst("p").text,
                            allLinks = selection("[href]") { findAll { eachHref } }
                        )
                    }
                }
            }
        }
        expectThat(extracted.paragraph).isEqualTo("i'm a paragraph")
        expectThat(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
        expectThat(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
    }

    @Test
    fun `can read and return html from file system with default charset (UTF-8) using the DSL`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(titleText).isEqualTo("i'm the title")
            this
        }
        expectThat(doc.titleText).isEqualTo("i'm the title")
    }

    @Test
    fun `can read and return html from file system using the DSL and non default charset`() {
        val doc = htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            title {
                findFirst {
                    text toBe "i'm the title"
                }
            }
            this
        }
        doc.title {
            findFirst {
                expectThat(text).isEqualTo("i'm the title")
            }
        }
    }

    @Test
    fun `can read and return html from String`() {
        htmlDocument(
            """
            <html>
                <body>
                    <h1>welcome</h1>
                    <div>
                        <p>first p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                </body>
            </html>"""
        ) {

            h1 {
                findFirst {
                    text toBe "welcome"
                }
            }
            p {
                withClass = "foo"
                findFirst {
                    text toBe "some p-element"
                    className toBe "foo"
                }
                findAll {
                    size toBe 2
                    expectThat(this.toString()).isEqualTo("[<p class=\"foo\">some p-element</p>, <p class=\"foo\">last p-element</p>]")
                }
            }
            p {
                findAll {
                    this.text toContain "p-element"
                }
                findLast {
                    text toBe "last p-element"
                }
            }
        }
    }

    @Test
    fun `can get parent of elements as well as of single element`() {
        htmlDocument(
            """
            <html>
                <body>
                    <h1>welcome</h1>
                    <div class="first-div">
                        <p>first p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                    <div class="second-div">
                        <p>second p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                </body>
            </html>"""
        ) {
            val paragraphsParent: List<DocElement> = p {
                findAll {
                    forEach {
                        // can get parent
                        expectThat(it.parent.tagName).isEqualTo("div")

                        // can invoke parent as lambda
                        it.parent {
                            expectThat(tagName).isEqualTo("div")
                        }
                    }

                    // can just extract paragraphs parents
                    map { it.parent }
                }
            }
            // since we have 6 paragraphs we get 6 times a parent
            expectThat(paragraphsParent).hasSize(6)
            expectThat(paragraphsParent.eachTagName).containsExactly("div", "div", "div", "div", "div", "div")
            expectThat(paragraphsParent.eachClassName).containsExactly("first-div", "second-div")

            // this also works for a single element
            val paragraphParent: DocElement = p {
                findFirst {
                    // can invoke parent as lambda
                    parent {
                        tagName toBe "div"
                    }

                    // can get/extract parent
                    parent
                }
            }
            paragraphParent.tagName toBe "div"
        }
    }

    @Test
    fun `can get siblings of elements as well as of single element`() {
        htmlDocument(
            """
            <html>
                <body>
                    <h1>welcome</h1>
                    <div class="first-div">
                        <p>first p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                    <div class="second-div">
                        <p>second p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                </body>
            </html>"""
        ) {
            val divsSiblings: List<DocElement> = div {
                findAll {
                    flatMap { it.siblings }
                }
            }

            // since we have 2 divs with 2 siblings each
            expectThat(divsSiblings).hasSize(4)
            expectThat(divsSiblings.eachTagName).containsExactly("h1", "div", "h1", "div")

            // this also works for a single element
            val divSiblings: List<DocElement> = div {
                findFirst {
                    // can invoke siblings as lambda
                    siblings {
                        expectThat(eachTagName).containsExactly("h1", "div")
                    }

                    // can get/extract siblings
                    siblings
                }
            }
            expectThat(divSiblings.eachTagName).containsExactly("h1", "div")
        }
    }

    @Test
    fun `can get children of elements as well as of single element`() {
        htmlDocument(
            """
            <html>
                <body>
                    <h1>welcome</h1>
                    <div class="first-div">
                        <p>first p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                    <div class="second-div">
                        <p>second p-element</p>
                        <p class="foo">some p-element</p>
                        <p class="foo">last p-element</p>
                    </div>
                </body>
            </html>"""
        ) {
            val divsChildren: List<DocElement> = div {
                findAll {
                    flatMap { it.children }
                }
            }

            // since we have 2 divs with 3 children each
            expectThat(divsChildren).hasSize(6)
            expectThat(divsChildren.eachTagName).containsExactly("p", "p", "p", "p", "p", "p")

            // this also works for a single element
            val divChildren: List<DocElement> = div {
                findFirst {
                    // can invoke children as lambda
                    children {
                        expectThat(eachTagName).containsExactly("p", "p", "p")
                    }

                    // can get/extract children
                    children
                }
            }
            expectThat(divChildren.eachText).containsExactly(
                "first p-element",
                "some p-element",
                "last p-element"
            )
        }
    }

    @Test
    fun `can extract link to directly call it afterwards`() {
        wiremock.setupStub(fileName = "example.html")
        wiremock.setupStub(fileName = "example.html", path = "/relative-link")

        val interestingLink = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            runBlocking {
                extract {
                    htmlDocument {
                        a {
                            findAll { first { it.ownText == "relative link" } }.attribute("href")
                        }
                    }
                }
            }
        }

        skrape(BrowserFetcher) {
            request {
                url = "${wiremock.httpUrl}$interestingLink"
            }
            expect {
                htmlDocument {
                    title {
                        findFirst { text toBe "i'm the title" }
                    }
                }
            }
        }
    }

    @Test
    fun `can read html from file system with default charset (UTF-8) using the DSL`() {
        htmlDocument(File("src/test/resources/__files/example.html")) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can read html from file system using the DSL and non default charset`() {
        htmlDocument(File("src/test/resources/__files/example.html"), Charsets.ISO_8859_1) {
            expectThat(titleText).isEqualTo("i'm the title")
        }
    }

    @Test
    fun `can scrape js rendered page`() {
        wiremock.setupStub(fileName = "js.html")

        skrape(BrowserFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
            }
            expect {
                htmlDocument {
                    div(".dynamic") {
                        findFirst {
                            text toBe "I have been dynamically added via Javascript"
                        }
                    }
                }
            }
        }
    }


    @Test
    fun `can preconfigure client`() {

        val fetcher: AsyncScraper<Request> = skrape(KtorFetcher) {
            request {
                url = "${wiremock.httpUrl}/"
                followRedirects = false
            }
        }

        wiremock.setupRedirect()

        val body1 = runBlocking {
            fetcher.extract {
                status {
                    code toBe 302
                    message toBe "Found"
                }
                responseBody
            }
        }

        wiremock.setupRedirect()

        val body2 = runBlocking {
            fetcher.apply {
                request {
                    followRedirects = true
                }
            }.extract {
                status {
                    code toBe 404
                    message toBe "Not Found"
                }
                responseStatus toBe HttpStatus.`4xx_Client_error`
                responseStatus toBe HttpStatus.`404_Not_Found`

                responseBody
            }
        }

        expectThat(body1).isNotEqualTo(body2)

    }

    @Test
    fun `can build url via dsl`() {
        val client = skrape(KtorFetcher) {
            request {
                url = urlBuilder {
                    protocol = UrlBuilder.Protocol.HTTPS
                    port = 12345
                    path = "/foo"
                    queryParam = mapOf("foo" to "bar", "fizz" to "buzz")
                    host = "foo.com"
                }
            }
        }

        expectThat(client.preparedRequest.url).isEqualTo("https://foo.com:12345/foo?foo=bar&fizz=buzz")
    }

    @Test
    fun `can convert DocElement to jsoup element`() {
        expectThat(aValidDocument().element).isA<Element>()
    }
}

