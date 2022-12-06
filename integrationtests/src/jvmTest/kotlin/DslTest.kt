import io.ktor.client.request.*
import io.ktor.http.*
import it.skrape.core.document
import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.matchers.*
import it.skrape.matchers.ContentTypes.*
import it.skrape.selects.*
import it.skrape.selects.html5.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.intellij.lang.annotations.Language
import org.jsoup.nodes.Element
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*
import java.io.File
import java.net.SocketTimeoutException
import kotlin.system.measureTimeMillis

private val wiremock = Testcontainer.wiremock
private val httpBin = Testcontainer.httpBin

@Execution(ExecutionMode.SAME_THREAD)
@DisabledOnOs(OS.WINDOWS)
class DslTest {

    @Test
    fun `dsl can skrape by url`() = runTest {
        wiremock.setupStub(path = "/example")
        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response {

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

    @Test
    fun `can call https with relaxed ssl option via DSL`() = runTest {
        wiremock.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpsUrl}/example")
                sslRelaxed = true
            }

            response { status { code toBe 200 } }
        }

    }

    @Test
    fun `dsl can assert content-type in highly readable way`() = runTest {
        wiremock.setupStub(path = "/example")

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/example")
            }

            response {
                contentType toContain TEXT_HTML
                contentType toBe TEXT_HTML_UTF8
                contentType toBeNot APPLICATION_XHTML
                contentType toBeNot APPLICATION_GZIP
                contentType toBeNot APPLICATION_JSON
                contentType toBeNot APPLICATION_TAR
                contentType toBeNot APPLICATION_XML
                contentType toBeNot APPLICATION_XUL
                contentType toBeNot APPLICATION_ZIP

                expectThat(contentType).isEqualTo("text/html;charset=UTF-8")
            }
        }
    }

    @Test
    fun `dsl will not follow redirects if configured`() = runTest {
        wiremock.setupRedirect()

        skrape(HttpFetcher) {
            request {
                followRedirects = false
                url("${wiremock.httpUrl}/")
            }

            response {
                status {
                    code toBe 302
                    message toBe "Found"
                }
            }
        }
    }

    @Test
    fun `dsl can check certain header`() = runTest {
        wiremock.setupStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
                val header = httpHeader("Content-Type") {
                    expectThat(this).isEqualTo("text/html; charset=UTF-8")
                }
                expectThat(header).isEqualTo("text/html; charset=UTF-8")

                val nonExistingHeader = httpHeader("Non-Existing") {
                    expectThat(this).isNull()
                }
                expectThat(nonExistingHeader).isNull()
            }
        }
    }

    @Test
    fun `dsl can check headers`() = runTest {
        wiremock.setupStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
                val headers = httpHeaders {
                    expectThat(this).hasEntry("Content-Type", "text/html; charset=UTF-8")
                }
                expectThat(headers).hasEntry("Content-Type", "text/html; charset=UTF-8")
            }
        }
    }

    @Test
    fun `dsl can get body`() = runTest {
        wiremock.setupStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
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

    @Test
    fun `dsl will follow redirect by default`() = runTest {
        wiremock.setupRedirect()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
                status {
                    code toBe 404
                    message toBe "Not Found"
                }
            }
        }
    }

    @Test
    fun `dsl can fetch url and use HTTP verb POST`() = runTest {
        wiremock.setupPostStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
                method = HttpMethod.Post
            }
            response {

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
                contentType toBe "application/json;charset=UTF-8"
            }
        }
    }

    @Test
    fun `dsl will throw exception on timeout`() = runTest {
        wiremock.setupStub(delay = 3000)

        expectThrows<SocketTimeoutException> {
            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                    timeout = 2000
                }
                response {}
            }
        }
    }

    class MyObject(
        var message: String? = null,
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
    )

    @Test
    fun `dsl can fetch url and extract to inferred type`() = runTest {
        wiremock.setupStub()

        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            val extracted = response {
                status {
                    MyObject(message, "", emptyList())
                }
            }
            expectThat(extracted.message).isEqualTo("OK")
        }
    }

    class MyOtherObject {
        lateinit var message: String
    }

    @Test
    fun `dsl can fetch url and extract using it`() = runTest {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            extractIt<MyOtherObject> {
                it.message = status { message }
            }
        }
        expectThat(extracted.message).isEqualTo("OK")
    }

    @Test
    fun `dsl can fetch url and extract using it in DSL-ish fashion`() = runTest {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            extractIt<MyObject> {
                it.message = status { message }
                htmlDocument {
                    it.allParagraphs = p { findAll { eachText } }
                    it.paragraph = findFirst("p").text
                    it.allLinks = findAll("[href]").eachHref
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

    data class MyDataClass(
        var httpStatusCode: Int = 0,
        var httpStatusMessage: String = "",
        var paragraph: String = "",
        var allParagraphs: List<String> = emptyList(),
        var allLinks: List<String> = emptyList()
    )

    /**
     * TODO: fix Class should have a single no-arg constructor: class MyDataClass
     * for classes or data classes that have none default values
     */
    @Test
    fun `dsl can fetch url and extract to data class`() = runTest {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

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

        expect {
            that(extracted.httpStatusCode).isEqualTo(200)
            that(extracted.httpStatusMessage).isEqualTo("OK")
            that(extracted.paragraph).isEqualTo("i'm a paragraph")
            that(extracted.allParagraphs).containsExactly("i'm a paragraph", "i'm a second paragraph")
            that(extracted.allLinks).containsExactly("http://some.url", "http://some-other.url", "/relative-link")
        }
    }

    data class MySimpleDataClass(
        val httpStatusCode: Int,
        val httpStatusMessage: String,
        val paragraph: String,
        val allParagraphs: List<String>,
        val allLinks: List<String>
    )

    @Test
    fun `dsl can fetch url and extract to data class without defaults`() = runTest {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            response {
                MySimpleDataClass(
                    httpStatusCode = status { code },
                    httpStatusMessage = status { message },
                    allParagraphs = document.p { findAll { eachText } },
                    paragraph = document.p { findFirst { text } },
                    allLinks = document.a { findAll { eachHref } }
                )
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
            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }
                response {
                    htmlDocument {
                        findFirst(".nonExistent") {}
                    }
                }
            }
        }
    }


    @Test
    fun `will return empty list if element could not be found and Doc mode is relaxed`() = runTest {
        skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
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

    @Test
    fun `will throw custom exception if element called by dsl could not be found`() {

        expectThrows<ElementNotFoundException> {
            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }
                response {
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

    @Test
    fun `dsl can fetch url and extract from skrape`() = runTest {
        wiremock.setupStub()

        val extracted = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }

            response {
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
    fun `can extract link to directly call it afterwards`() = runTest {
        wiremock.setupStub(fileName = "example.html")
        wiremock.setupStub(fileName = "example.html", path = "/relative-link")

        val interestingLink = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
                htmlDocument {
                    a {
                        findAll { first { it.ownText == "relative link" } }.attribute("href")
                    }
                }
            }
        }

        skrape(BrowserFetcher) {
            request {
                url("${wiremock.httpUrl}$interestingLink")
            }
            response {
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
    fun `can scrape js rendered page`() = runTest {
        wiremock.setupStub(fileName = "js.html")

        skrape(BrowserFetcher) {
            request {
                url("${wiremock.httpUrl}/")
            }
            response {
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
    fun `can skrape none blocking inside a coroutine`() = runTest {
        wiremock.setupStub(path = "/delayed", delay = 5_000)

        val asynExecTimeInMillis = measureTimeMillis {
            runBlocking {
                repeat(5) {
                    launch {
                        skrape(HttpFetcher) {
                            request {
                                url("${wiremock.httpUrl}/delayed")
                                timeout = 20_000
                            }
                            response {
                                status {
                                    code toBe 200
                                    message toBe "OK"
                                }
                                htmlDocument {
                                    title {
                                        findFirst { text toBe "i'm the title" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        expectThat(asynExecTimeInMillis).isLessThan(20_000)

        val sequentialExecTimeInMillis = measureTimeMillis {
            repeat(5) {
                skrape(HttpFetcher) {
                    request {
                        url("${wiremock.httpUrl}/delayed")
                        timeout = 15_000
                    }
                    response {
                        status {
                            code toBe 200
                            message toBe "OK"
                        }
                        htmlDocument {
                            title {
                                findFirst { text toBe "i'm the title" }
                            }
                        }
                    }
                }
            }
        }


        expectThat(sequentialExecTimeInMillis).isGreaterThan(25000)
    }

    @Test
    fun `can use preconfigured client`() = runTest {

        val fetcher: Scraper = skrape(HttpFetcher) {
            request {
                url("${wiremock.httpUrl}/")
                followRedirects = false
            }
        }

        wiremock.setupRedirect()

        // TODO: add possibility to call extract / expect outside of a coroutine scoope
        val body1 = fetcher.extractBlocking {
            status {
                code toBe 302
                message toBe "Found"
            }
            responseBody
        }
        wiremock.setupRedirect()

        val body2 = fetcher.apply {
            request {
                followRedirects = true
            }
        }.extractBlocking {
            status {
                code toBe 404
                message toBe "Not Found"
            }
            responseStatus toBe HttpStatus.`4xx_Client_error`
            responseStatus toBe HttpStatus.`404_Not_Found`

            responseBody
        }

        expectThat(body1).isNotEqualTo(body2)

        fetcher.expectBlocking {
            status {
                code toBe 404
            }
        }

        val statusCode = fetcher.extractBlocking {
            status {
                code
            }
        }

        expectThat(statusCode).isEqualTo(404)

        data class MyResult(
            var statusCode: Int = 0
        )

        val myResult = fetcher.extractItBlocking<MyResult> { it.statusCode = status { code } }
        expectThat(myResult.statusCode).isEqualTo(404)

    }

    @Test
    fun `can build url via dsl`() = runTest {
        val client = skrape(HttpFetcher) {
            request {
                method = HttpMethod.Post // defaults to GET
                url("https://foo.com:12345/foo#bar?xxx")
                url {
                    protocol = URLProtocol.HTTPS
                    port = 12345
                    path("/foo")
                    host = "foo.com"
                    queryParam {
                        "foo" to "bar"
                        "fizz" to "buzz"
                        +"someKey"
                        "bar" to null
                        "afew" to listOf("1", 2, .4711, null)
                    }
                    fragment = "modal"
                }
            }
        }

        expectThat(client.requestBuilder.url.toString())
            .isEqualTo("https://foo.com:12345/foo#modal?foo=bar&fizz=buzz&bar=null&afew=1,2,0.4711,null&someKey")
    }

    @Test
    fun `can convert DocElement to jsoup element`() {
        expectThat(aValidDocument().element).isA<Element>()
    }

    @Test
    fun `can scrape our docs page with JS-rendering`() = runTest {
        skrape(BrowserFetcher) {
            request {
                url("https://docs.skrape.it/docs/")
            }

            response {
                htmlDocument {
                    toString() toContain "Focus and Paradigms"
                }
            }
        }
    }

    @Test
    fun `can scrape our docs page without JS-rendering`() = runTest {
        skrape(HttpFetcher) {
            request {
                url("https://docs.skrape.it/docs/")
            }
            response {
                htmlDocument {
                    toString() toContain "Focus and Paradigms"
                }
            }
        }
    }

    @Test
    fun `can NOT scrape basic auth protected websites without credentials`() = runTest {

        skrape {
            request {
                url("$httpBin/basic-auth/cr1z/secure")
            }

            response {
                status {
                    code toBe 401
                    message toBe "UNAUTHORIZED"
                }
            }
        }
    }

    @Test
    fun `can scrape basic auth protected websites`() = runTest {

        skrape {
            request {
                url("$httpBin/basic-auth/cr1z/secure")

                authentication = basic {
                    username = "cr1z"
                    password = "secure"
                }
            }

            response {
                status {
                    code toBe 200
                }
                responseBody toContain """authenticated": true"""
                responseBody toContain """user": "cr1z"""
            }
        }
    }

    @Test
    fun `can invoke a raw nested css-selector`() {
        @Language("HTML") val myMarkUp = """
            <div class="CollapsiblePanelTab" tabindex="0">Today's Interest (1)</div>
                <div class="CollapsiblePanelContent">
                <table width="667px" class="tabularData">
                    <tr>
                        <td width="407px" height="21"><a href="link info i need in here">description </a></td>
                        <td width="130px">15:28</td>
                        <td width="130px">Western</td>
                    </tr> 
                </table>
            </div>
        """.trimIndent()

        val tdsWithLink = htmlDocument(myMarkUp) {
            "table tr td a" {
                withAttributeKey = "href"
                findAll { this }
            }
        }

        expectThat(tdsWithLink)
            .hasSize(1)
            .get { attribute("href") }.isEqualTo("link info i need in here")


    }

    @Test
    fun `can nest selection via css selectors`() {
        @Language("HTML") val myMarkUp = """
            <div class="foo">
                <div class="bar">
                    <div>first nested div</div>
                </div>
            </div>
            <div class="foo">
                <div class="bar">
                    <div>other nested div</div>
                </div>
            </div>
            <div class="some-other"></div>
        """.trimIndent()

        htmlDocument(myMarkUp) {

            div {
                withClass = "foo"

                div {
                    withClass = "bar"

                    findAll {
                        text toBe "first nested div other nested div"
                    }

                    findFirst {
                        text toBe "first nested div"
                    }
                }
            }
        }
    }

    @Test
    fun `can handle attributes with spaces in the value`() {
        @Language("HTML") val myMarkUp = """
            <div data-status="Important Value">
                Text1
            </div>
            <div data-status="NoSpaceInThisOne">
                Text2
            </div>
            <div data-status=" Spaces everywhere  ">
                Text3
            </div>
        """.trimIndent()

        htmlDocument(myMarkUp) {

            div {
                withAttribute = Pair("data-status", "NoSpaceInThisOne")
                findAll {
                    text toBe "Text2"
                }
            }

            div {
                withAttribute = Pair("data-status", "Important Value")
                findAll {
                    text toBe "Text1"
                }
            }

            div {
                withAttribute = "data-status" to " Spaces everywhere  "
                findAll {
                    text toBe "Text3"
                }
            }
        }
    }

    @Test
    fun `relaxed mode will not throw exception if element not exists`() {
        htmlDocument("""<span class="xxx"">hello</span>""") {
            relaxed = true
            span {
                withClass = "xxx"
                findAll { toBeNotEmpty }
                findFirst { text toBe "hello" }
            }
            span {
                withClass = "yyy"
                // in none relaxed mode it would throw an ElementNotFoundException when trying to find element without success
                findAll { toBeEmpty }
                findFirst { text toBe "" }
            }
            "some.crazy selectorThat[doesnt] exists" {
                // in none relaxed mode it would throw an ElementNotFoundException when trying to find element without success
                findAll { toBeEmpty }
            }
        }
    }
}
