@file:Suppress("FunctionName", "EnumEntryName")

package it.skrape.matchers

import io.ktor.http.*
import kotlin.js.JsName

@Suppress("EnumNaming", "MagicNumber", "unused")
@Deprecated("Use Ktor types", ReplaceWith("HttpStatusCode", "io.ktor.http.HttpStatusCode"))
public enum class HttpStatus(public val code: Int, public val message: String) {
    @JsName("_1xxInformationalResponse")
	`1xx_Informational_response`(1, "Informational response"),
    @JsName("_100Continue")
	`100_Continue`(100, "Continue"),
    @JsName("_101SwitchingProtocols")
	`101_Switching_Protocols`(101, "Switching_Protocols"),
    @JsName("_102Processing")
	`102_Processing`(101, "Processing"),
    @JsName("_103EarlyHints")
	`103_Early_Hints`(101, "Early Hints"),

    @JsName("_2xxSuccessful")
	`2xx_Successful`(2, "Successful"),
    @JsName("_200OK")
	`200_OK`(200, "OK"),
    @JsName("_201Created")
	`201_Created`(201, "Created"),
    @JsName("_202Accepted")
	`202_Accepted`(202, "Accepted"),
    @JsName("_203NonAuthoritativeInformation")
	`203_Non-Authoritative_Information`(203, "Non-Authoritative Information"),
    @JsName("_204NoContent")
	`204_No_Content`(204, "No Content"),
    @JsName("_205ResetContent")
	`205_Reset_Content`(205, "Reset Content"),
    @JsName("_206PartialContent")
	`206_Partial_Content`(206, "Partial Content"),
    @JsName("_207MultiStatus")
	`207_Multi-Status`(207, "Multi-Status"),
    @JsName("_208AlreadyReported")
	`208_Already_Reported`(208, "Already Reported"),
    @JsName("_226IMUsed")
	`226_IM_Used`(226, "IM Used"),

    @JsName("_3xxRedirection")
	`3xx_Redirection`(3, "Redirection"),
    @JsName("_300MultipleChoices")
	`300_Multiple_Choices`(300, "Multiple Choices"),
    @JsName("_301MovedPermanently")
	`301_Moved_Permanently`(301, "Moved Permanently"),
    @JsName("_302Found")
	`302_Found`(302, "Found"),
    @JsName("_303SeeOther")
	`303_See_Other`(303, "See Other"),
    @JsName("_304NotModified")
	`304_Not_Modified`(304, "Not Modified"),
    @JsName("_305UseProxy")
	`305_Use_Proxy`(305, "Use Proxy"),
    @JsName("_306SwitchProxy")
	`306_Switch_Proxy`(306, "Switch Proxy"),
    @JsName("_307TemporaryRedirect")
	`307_Temporary_Redirect`(307, "Temporary Redirect"),
    @JsName("_308PermanentRedirect")
	`308_Permanent_Redirect`(308, "Permanent Redirect"),

    @JsName("_4xxClientError")
	`4xx_Client_error`(4, "Client error"),
    @JsName("_400BadRequest")
	`400_Bad_Request`(400, "Bad Request"),
    @JsName("_401Unauthorized")
	`401_Unauthorized`(401, "Unauthorized"),
    @JsName("_402PaymentRequired")
	`402_Payment_Required`(402, "Payment Required"),
    @JsName("_403Forbidden")
	`403_Forbidden`(403, "Forbidden"),
    @JsName("_404NotFound")
	`404_Not_Found`(404, "Not Found"),
    @JsName("_405MethodNotAllowed")
	`405_Method_Not_Allowed`(405, "Method Not Allowed"),
    @JsName("_406NotAcceptable")
	`406_Not_Acceptable`(406, "Not Acceptable"),
    @JsName("_407ProxyAuthenticationRequired")
	`407_Proxy_Authentication_Required`(407, "Proxy Authentication Required"),
    @JsName("_408RequestTimeout")
	`408_Request_Timeout`(408, "Request Timeout"),
    @JsName("_409Conflict")
	`409_Conflict`(409, "Conflict"),
    @JsName("_410Gone")
	`410_Gone`(410, "Gone"),
    @JsName("_411LengthRequired")
	`411_Length_Required`(411, "Length Required"),
    @JsName("_412PreconditionFailed")
	`412_Precondition_Failed`(412, "Precondition Failed"),
    @JsName("_413PayloadTooLarge")
	`413_Payload_Too_Large`(413, "Payload Too Large"),
    @JsName("_414URITooLong")
	`414_URI_Too_Long`(414, "URI Too Long"),
    @JsName("_415UnsupportedMediaType")
	`415_Unsupported_Media_Type`(415, "Unsupported Media Type"),
    @JsName("_416RangeNotSatisfiable")
	`416_Range_Not_Satisfiable`(416, "Range Not Satisfiable"),
    @JsName("_417ExpectationFailed")
	`417_Expectation_Failed`(417, "Expectation Failed"),
    @JsName("_418IAmATeapot")
	`418_I_am_a_teapot`(418, "I'm a teapot"),
    @JsName("_421MisdirectedRequest")
	`421_Misdirected_Request`(421, "Misdirected Request"),
    @JsName("_422UnprocessableEntity")
	`422_Unprocessable_Entity`(422, "Unprocessable Entity"),
    @JsName("_423Locked")
	`423_Locked`(423, "Locked"),
    @JsName("_424FailedDependency")
	`424_Failed_Dependency`(424, "Failed Dependency"),
    @JsName("_425TooEarly")
	`425_Too_Early`(425, "Too Early"),
    @JsName("_426UpgradeRequired")
	`426_Upgrade_Required`(426, "Upgrade Required"),
    @JsName("_428PreconditionRequired")
	`428_Precondition_Required`(428, "Precondition Required"),
    @JsName("_429TooManyRequests")
	`429_Too_Many_Requests`(429, "Too Many Requests"),
    @JsName("_431RequestHeaderFieldsToo_Large")
	`431_Request_Header_Fields_Too_Large`(431, "Request Header Fields Too Large"),
    @JsName("_451UnavailableForLegalReasons")
	`451_Unavailable_For_Legal_Reasons`(451, "Unavailable For Legal Reasons"),

    @JsName("_5xxServerError")
	`5xx_Server_error`(5, "Server error"),
    @JsName("_500InternalServerError")
	`500_Internal_Server_Error`(500, "Internal Server Error"),
    @JsName("_501NotImplemented")
	`501_Not_Implemented`(501, "Not Implemented"),
    @JsName("_502BadGateway")
	`502_Bad_Gateway`(502, "Bad Gateway"),
    @JsName("_503ServiceUnavailable")
	`503_Service_Unavailable`(503, "Service Unavailable"),
    @JsName("_504GatewayTimeout")
	`504_Gateway_Timeout`(504, "Gateway Timeout"),
    @JsName("_505HTTPVersionNotSupported")
	`505_HTTP_Version_Not_Supported`(505, "HTTP Version Not Supported"),
    @JsName("_506VariantAlsoNegotiates")
	`506_Variant_Also_Negotiates`(506, "Variant Also Negotiates"),
    @JsName("_507InsufficientStorage")
	`507_Insufficient_Storage`(507, "Insufficient Storage"),
    @JsName("_508LoopDetected")
	`508_Loop_Detected`(508, "Loop Detected"),
    @JsName("_509NotExtended")
	`509_Not_Extended`(509, "Not Extended"),
    @JsName("_510Gone")
	`510_Gone`(510, "Gone"),
    @JsName("_511NetworkAuthenticationRequired")
	`511_Network_Authentication_Required`(511, "Network Authentication Required");

    public fun toStatus(): HttpStatusCode = HttpStatusCode(code, message)
}

private val informationalResponse = HttpStatusCode(1, "Informational Response")
private val successful = HttpStatusCode(2, "Successful")
private val redirection = HttpStatusCode(3, "Redirection")
private val clientError = HttpStatusCode(4, "Client Error")
private val serverError = HttpStatusCode(5, "Server Error")
private val groupStatusCodes = listOf(informationalResponse, successful, redirection, clientError, serverError)

val HttpStatusCode.Companion.InformationalResponse
    get() = informationalResponse
val HttpStatusCode.Companion.Successful
    get() = successful
val HttpStatusCode.Companion.Redirection
    get() = redirection
val HttpStatusCode.Companion.ClientError
    get() = clientError
val HttpStatusCode.Companion.ServerError
    get() = serverError

val HttpStatusCode.Companion.allGroupStatusCodes
    get() = groupStatusCodes

@Deprecated("Use Ktor Types", ReplaceWith("toBeExactly"))
public infix fun HttpStatusCode.toBe(expected: HttpStatus): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(this.value / 100 == expected.code, expected.toStatus())
    }
    return statusAssertion(this == expected.toStatus(), expected.toStatus())
}

public infix fun HttpStatusCode.toBe(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 == expected.value, expected)
    }
    return statusAssertion(this == expected && this.description == expected.description, expected)
}

public infix fun HttpStatusCode.toBeExactly(expected: HttpStatusCode): HttpStatusCode {
    if (expected.value > 5) statusAssertion(description == expected.description, expected)
    return toBe(expected)
}

@Deprecated("Use Ktor Types", ReplaceWith("notToBeExactly"))
public infix fun HttpStatusCode.toBeNot(expected: HttpStatus): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(this.value / 100 != expected.code, expected.toStatus())
    }
    return statusAssertion(this != expected.toStatus(), expected.toStatus())
}

public infix fun HttpStatusCode.notToBe(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 != expected.value, expected)
    }
    return statusAssertion(this != expected, expected)
}

public infix fun HttpStatusCode.notToBeExactly(expected: HttpStatusCode): HttpStatusCode {
    @Suppress("MagicNumber")
    if (expected.value <= 5) {
        return statusAssertion(this.value / 100 != expected.value || this.description != expected.description, expected)
    }
    return statusAssertion(this.value != expected.value || this.description != expected.description, expected)
}
