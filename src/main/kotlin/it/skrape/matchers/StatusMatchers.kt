package it.skrape.matchers

import it.skrape.core.fetcher.Result

@Suppress("EnumNaming", "MagicNumber")
enum class HttpStatus(val code: Int, val message: String) {
    `1xx Informational response`(1, "Informational response"),
    `100 Continue`(100, "Continue"),
    `101 Switching_Protocols`(101, "Switching_Protocols"),
    `102 Processing`(101, "Processing"),
    `103 Early Hints`(101, "Early Hints"),

    `2xx Successful`(2, "Successful"),
    `200 OK`(200, "OK"),
    `201 Created`(201, "Created"),
    `202 Accepted`(202, "Accepted"),
    `203 Non-Authoritative Information`(203, "Non-Authoritative Information"),
    `204 No Content`(204, "No Content"),
    `205 Reset Content`(205, "Reset Content"),
    `206 Partial Content`(206, "Partial Content"),
    `207 Multi-Status`(207, "Multi-Status"),
    `208 Already Reported`(208, "Already Reported"),
    `226 IM Used`(226, "IM Used"),

    `3xx Redirection`(3, "Redirection"),
    `300 Multiple Choices`(300, "Multiple Choices"),
    `301 Moved Permanently`(301, "Moved Permanently"),
    `302 Found`(302, "Found"),
    `303 See Other`(303, "See Other"),
    `304 Not Modified`(304, "Not Modified"),
    `305 Use Proxy`(305, "Use Proxy"),
    `306 Switch Proxy`(306, "Switch Proxy"),
    `307 Temporary Redirect`(307, "Temporary Redirect"),
    `308 Permanent Redirect`(308, "Permanent Redirect"),

    `4xx Client error`(4, "Client error"),
    `400 Bad Request`(400, "Bad Request"),
    `401 Unauthorized`(401, "Unauthorized"),
    `402 Payment Required`(402, "Payment Required"),
    `403 Forbidden`(403, "Forbidden"),
    `404 Not Found`(404, "Not Found"),
    `405 Method Not Allowed`(405, "Method Not Allowed"),
    `406 Not Acceptable`(406, "Not Acceptable"),
    `407 Proxy Authentication Required`(407, "Proxy Authentication Required"),
    `408 Request Timeout`(408, "Request Timeout"),
    `409 Conflict`(409, "Conflict"),
    `410 Gone`(410, "Gone"),
    `411 Length Required`(411, "Length Required"),
    `412 Precondition Failed`(412, "Precondition Failed"),
    `413 Payload Too Large`(413, "Payload Too Large"),
    `414 URI Too Long`(414, "URI Too Long"),
    `415 Unsupported Media Type`(415, "Unsupported Media Type"),
    `416 Range Not Satisfiable`(416, "Range Not Satisfiable"),
    `417 Expectation Failed`(417, "Expectation Failed"),
    `418 I'm a teapot`(418, "I'm a teapot"),
    `421 Misdirected Request`(421, "Misdirected Request"),
    `422 Unprocessable Entity`(422, "Unprocessable Entity"),
    `423 Locked`(423, "Locked"),
    `424 Failed Dependency`(424, "Failed Dependency"),
    `425 Too Early`(425, "Too Early"),
    `426 Upgrade Required`(426, "Upgrade Required"),
    `428 Precondition Required`(428, "Precondition Required"),
    `429 Too Many Requests`(429, "Too Many Requests"),
    `431 Request Header Fields Too Large`(431, "Request Header Fields Too Large"),
    `451 Unavailable For Legal Reasons`(451, "Unavailable For Legal Reasons"),

    `5xx Server error`(5, "Server error"),
    `500 Internal Server Error`(500, "Internal Server Error"),
    `501 Not Implemented`(501, "Not Implemented"),
    `502 Bad Gateway`(502, "Bad Gateway"),
    `503 Service Unavailable`(503, "Service Unavailable"),
    `504 Gateway Timeout`(504, "Gateway Timeout"),
    `505 HTTP Version Not Supported`(505, "HTTP Version Not Supported"),
    `506 Variant Also Negotiates`(506, "Variant Also Negotiates"),
    `507 Insufficient Storage`(507, "Insufficient Storage"),
    `508 Loop Detected`(508, "Loop Detected"),
    `509 Not Extended`(509, "Not Extended"),
    `510 Gone`(510, "Gone"),
    `511 Network Authentication Required`(511, "Network Authentication Required");

    fun toStatus() = Result.Status(code, message)
}

infix fun Result.Status.toBe(expected: HttpStatus): Result.Status {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(this.code.toString().startsWith(expected.code.toString()), expected.toStatus())
    }
    return statusAssertion(this == expected.toStatus(), expected.toStatus())
}

infix fun Result.Status.`to be`(expected: HttpStatus) = this toBe expected

infix fun Result.Status.toBeNot(expected: HttpStatus): Result.Status {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(!this.code.toString().startsWith(expected.code.toString()), expected.toStatus())
    }
    return statusAssertion(this != expected.toStatus(), expected.toStatus())
}

infix fun Result.Status.`to be not`(expected: HttpStatus) = this toBeNot expected
