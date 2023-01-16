import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.errors.*
import it.skrape.core.document
import it.skrape.core.htmlDocument
import it.skrape.fetcher.*
import it.skrape.matchers.*
import it.skrape.selects.*
import it.skrape.selects.html5.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
class DslTest : FunSpec() {
    init {
        extension(DockerExtension)
        extension(TestcontainerExtension)

        test("dsl can skrape by url").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub(path = "/example")
            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/example")
                }

                response {

                    status {
                        println("Code is $value")
                        value toBe 200
                        description toBe "OK"
                    }

                    contentType() toBeExactly ContentType.Text.Html.withParameter("charset", "UTF-8")

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

        test("can call https with relaxed ssl option via DSL").config(
            enabledOrReasonIf = (DockerExtension.isAvailable and dontRunOnPlatform(
                Platform.JS
            ))
        ) {
            wiremock.setupStub(path = "/example")

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpsUrl}/example")
                    sslRelaxed = true
                }

                response { status { value toBe 200 } }
            }

        }

        test("dsl can assert content-type in highly readable way").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub(path = "/example")

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/example")
                }

                response {
                    contentType() toMatch ContentType.Text.Html
                    contentType() toBeExactly ContentType.Text.Html.withParameter("charset", "UTF-8")
                    contentType() notToBe ContentType.Application.Xml

                    contentType().shouldBe(ContentType.Text.Html.withParameter("charset", "UTF-8"))
                }
            }
        }

        //Don't run in the browser since fetch limitations prevent custom handling of redirects
        test("dsl will not follow redirects if configured").config(
            enabledOrReasonIf = (DockerExtension.isAvailable and dontRunOnPlatform(
                Platform.WEB
            ))
        ) {
            wiremock.setupRedirect()

            skrape(HttpFetcher) {
                request {
                    followRedirects = false
                    url("${wiremock.httpUrl}/")
                }

                response {
                    status {
                        value toBe 302
                        description toBe "Found"
                    }
                }
            }
        }

        test("dsl can check certain header").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub()

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }
                response {
                    val header = httpHeader("Content-Type") { //Fails header null
                        this.shouldBe("text/html; charset=UTF-8")
                    }
                    header.shouldBe("text/html; charset=UTF-8")

                    val nonExistingHeader = httpHeader("Non-Existing") {
                        this.shouldBeNull()
                    }
                    nonExistingHeader.shouldBeNull()
                }
            }
        }

        test("dsl can check headers").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub()

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }
                response {
                    val headers = httpHeaders {
                        this["Content-Type"].shouldBe("text/html; charset=UTF-8")
                    }
                    headers["Content-Type"].shouldBe("text/html; charset=UTF-8")
                }
            }
        }

        test("dsl can get body").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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

        test("dsl will follow redirect by default").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupRedirect()

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }
                response {
                    status {
                        value toBe 404
                        description toBe "Not Found"
                    }
                }
            }
        }

        test("dsl can fetch url and use HTTP verb POST").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupPostStub()

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                    method = HttpMethod.Post
                }
                response {

                    //request.method.shouldBe(Method.POST)

                    status {
                        value toBe 200
                        description toBe "OK"
                    }

                    status toBe HttpStatusCode.Successful
                    status toBe HttpStatusCode.OK
                    status notToBe HttpStatusCode.InformationalResponse
                    status notToBe HttpStatusCode.Redirection
                    status notToBe HttpStatusCode.ClientError
                    status notToBe HttpStatusCode.ServerError

                    contentType() toBeExactly ContentType.Application.Json.withParameter("charset", "UTF-8")
                    contentType() toBeExactly "application/json; charset=UTF-8"
                }
            }
        }

        test("dsl will throw exception on timeout").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub(delay = 3000)

            try {
                skrape(HttpFetcher) {
                    request {
                        url("${wiremock.httpUrl}/")
                        timeout = 2000
                    }
                    response {}
                }
            } catch (ex: Exception) {
                //JS wraps the actual exception in a CancellaionException
                val tEx = if (ex is CancellationException) ex.cause else ex
                //This expectation is weird since the timeout can be either of 3 exceptions which are all valid
                tEx.shouldBeInstanceOf<IOException>()
                println(tEx::class)
                tEx::class.shouldBeOneOf(
                    io.ktor.client.network.sockets.SocketTimeoutException::class,
                    io.ktor.client.network.sockets.ConnectTimeoutException::class,
                    HttpRequestTimeoutException::class
                )
            }
        }

        class MyObject(
            var message: String? = null,
            var paragraph: String = "",
            var allParagraphs: List<String> = emptyList(),
            var allLinks: List<String> = emptyList()
        )

        test("dsl can fetch url and extract to inferred type").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub()

            skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }

                val extracted = response {
                    status {
                        MyObject(description, "", emptyList())
                    }
                }
                extracted.message.shouldBe("OK")
            }
        }

        data class MySimpleDataClass(
            val httpStatusCode: Int,
            val httpStatusMessage: String,
            val paragraph: String,
            val allParagraphs: List<String>,
            val allLinks: List<String>
        )

        test("dsl can fetch url and extract to data class without defaults").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub()

            val extracted = skrape(HttpFetcher) {
                request {
                    url("${wiremock.httpUrl}/")
                }

                response {
                    MySimpleDataClass(
                        httpStatusCode = status { value },
                        httpStatusMessage = status { description },
                        allParagraphs = document.p { findAll { eachText } },
                        paragraph = document.p { findFirst { text } },
                        allLinks = document.a { findAll { eachHref } }
                    )
                }
            }

            assertSoftly(extracted) {
                httpStatusCode.shouldBe(200)
                httpStatusMessage.shouldBe("OK")
                paragraph.shouldBe("i'm a paragraph")
                allParagraphs.shouldContainExactly("i'm a paragraph", "i'm a second paragraph")
                allLinks.shouldContainExactly("http://some.url", "http://some-other.url", "/relative-link")
            }
        }

        test("will throw custom exception if element could not be found via lambda").config(enabledOrReasonIf = DockerExtension.isAvailable) {

            shouldThrow<ElementNotFoundException> {
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


        test("will return empty list if element could not be found and Doc mode is relaxed").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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

        test("will throw custom exception if element called by dsl could not be found").config(enabledOrReasonIf = DockerExtension.isAvailable) {

            shouldThrow<ElementNotFoundException> {
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

        test("dsl can fetch url and extract from skrape").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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
            extracted.paragraph.shouldBe("i'm a paragraph")
            extracted.allParagraphs.shouldContainExactly("i'm a paragraph", "i'm a second paragraph")
            extracted.allLinks.shouldContainExactly("http://some.url", "http://some-other.url", "/relative-link")
        }

        test("can read and return html from file system with default charset (UTF-8) using the DSL") {
            val doc = htmlDocument(getInputFromFile("example.html")) {
                titleText.shouldBe("i'm the title")
                this
            }
            doc.titleText.shouldBe("i'm the title")
        }

        test("can read and return html from file system using the DSL and non default charset") {
            val doc = htmlDocument(getInputFromFile("example.html"), Charsets.ISO_8859_1) {
                title {
                    findFirst {
                        text toBe "i'm the title"
                    }
                }
                this
            }
            doc.title {
                findFirst {
                    text.shouldBe("i'm the title")
                }
            }
        }

        test("can read and return html from String") {
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
                        this.toString()
                            .shouldBe("[<p class=\"foo\">some p-element</p>, <p class=\"foo\">last p-element</p>]")
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

        test("can get parent of elements as well as of single element") {
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
                            it.parent.tagName.shouldBe("div")

                            // can invoke parent as lambda
                            it.parent {
                                tagName.shouldBe("div")
                            }
                        }

                        // can just extract paragraphs parents
                        map { it.parent }
                    }
                }
                // since we have 6 paragraphs we get 6 times a parent
                paragraphsParent.shouldHaveSize(6)
                paragraphsParent.eachTagName.shouldContainExactly("div", "div", "div", "div", "div", "div")
                paragraphsParent.eachClassName.shouldContainExactly("first-div", "second-div")

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

        test("can get siblings of elements as well as of single element") {
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
                        this@findAll.flatMap { it.siblings }
                    }
                }

                // since we have 2 divs with 2 siblings each
                divsSiblings.shouldHaveSize(4)
                divsSiblings.eachTagName.shouldContainExactlyInAnyOrder("h1", "div", "h1", "div")

                // this also works for a single element
                val divSiblings: List<DocElement> = div {
                    findFirst {
                        // can invoke siblings as lambda
                        siblings {
                            eachTagName.shouldContainExactlyInAnyOrder("h1", "div")
                        }

                        // can get/extract siblings
                        siblings
                    }
                }
                divSiblings.eachTagName.shouldContainExactlyInAnyOrder("h1", "div")
            }
        }

        test("can get children of elements as well as of single element") {
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
                        this@findAll.flatMap { it.children }
                    }
                }

                // since we have 2 divs with 3 children each
                divsChildren.shouldHaveSize(6)
                divsChildren.eachTagName.shouldContainExactly("p", "p", "p", "p", "p", "p")

                // this also works for a single element
                val divChildren: List<DocElement> = div {
                    findFirst {
                        // can invoke children as lambda
                        children {
                            eachTagName.shouldContainExactly("p", "p", "p")
                        }

                        // can get/extract children
                        children
                    }
                }
                divChildren.eachText.shouldContainExactly(
                    "first p-element",
                    "some p-element",
                    "last p-element"
                )
            }
        }

        test("can extract link to directly call it afterwards").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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

        test("can read html from file system with default charset (UTF-8) using the DSL") {
            htmlDocument(getInputFromFile("example.html")) {
                titleText.shouldBe("i'm the title")
            }
        }

        test("can read html from file system using the DSL and non default charset") {
            htmlDocument(getInputFromFile("example.html"), Charsets.ISO_8859_1) {
                titleText.shouldBe("i'm the title")
            }
        }

        test("can scrape js rendered page").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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

        test("can skrape none blocking inside a coroutine").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            wiremock.setupStub(path = "/delayed", delay = 5_000)

            val asyncExecTimeInMillis = measureTime {
                repeat(5) {
                    launch {
                        skrape(HttpFetcher) {
                            request {
                                url("${wiremock.httpUrl}/delayed")
                                timeout = 20_000
                            }
                            response {
                                status {
                                    value toBe 200
                                    description toBe "OK"
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

            asyncExecTimeInMillis.inWholeMilliseconds.shouldBeLessThan(20_000)

            val sequentialExecTimeInMillis = measureTime {
                repeat(5) {
                    skrape(HttpFetcher) {
                        request {
                            url("${wiremock.httpUrl}/delayed")
                            timeout = 20_000
                        }
                        response {
                            status {
                                value toBe 200
                                description toBe "OK"
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


            sequentialExecTimeInMillis.inWholeMilliseconds.shouldBeGreaterThan(25000)
        }

        test("can build url via dsl").config(enabledOrReasonIf = DockerExtension.isAvailable) {
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

            client.requestBuilder.url.buildString()
                .shouldBe("https://foo.com:12345/foo?foo=bar&fizz=buzz&someKey&bar=null&afew=1&afew=2&afew=0.4711&afew=null#modal")
        }

        //TODO The website causes an error with node-fetch for some reason
        //Disabled on web because of CORS issues
        test("can scrape our docs page with JS-rendering").config(enabledOrReasonIf = dontRunOnPlatform(Platform.JS)) {
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

        //Disabled on web because of CORS issues
        test("can scrape our docs page without JS-rendering").config(enabledOrReasonIf = dontRunOnPlatform(Platform.JS)) {
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

        test("can NOT scrape basic auth protected websites without credentials").config(enabledOrReasonIf = DockerExtension.isAvailable) {

            skrape {
                request {
                    url("$httpBin/basic-auth/cr1z/secure")
                }

                response {
                    status {
                        value toBe 401
                        description toBe "UNAUTHORIZED"
                    }
                }
            }
        }

        test("can scrape basic auth protected websites").config(enabledOrReasonIf = DockerExtension.isAvailable) {

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
                        value toBe 200
                    }
                    bodyAsText() toContain """authenticated": true"""
                    bodyAsText() toContain """user": "cr1z"""
                }
            }
        }

        test("can invoke a raw nested css-selector") {
            val myMarkUp = """
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

            tdsWithLink.shouldHaveSize(1)
            tdsWithLink[0].attribute("href").shouldBe("link info i need in here")


        }

        test("can nest selection via css selectors") {
            val myMarkUp = """
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

        test("can handle attributes with spaces in the value") {
            val myMarkUp = """
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

        test("relaxed mode will not throw exception if element not exists") {
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
}