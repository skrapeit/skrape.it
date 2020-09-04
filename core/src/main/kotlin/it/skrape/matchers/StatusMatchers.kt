@file:Suppress("FunctionName", "EnumEntryName")

package it.skrape.matchers

import it.skrape.core.fetcher.Result

@Suppress("EnumNaming", "MagicNumber", "unused")
enum class HttpStatus(val code: Int, val message: String) {
    `1xx_Informational_response`(1, "Informational response"),
    `100_Continue`(100, "Continue"),
    `101_Switching_Protocols`(101, "Switching_Protocols"),
    `102_Processing`(101, "Processing"),
    `103_Early_Hints`(101, "Early Hints"),

    `2xx_Successful`(2, "Successful"),
    `200_OK`(200, "OK"),
    `201_Created`(201, "Created"),
    `202_Accepted`(202, "Accepted"),
    `203_Non-Authoritative_Information`(203, "Non-Authoritative Information"),
    `204_No_Content`(204, "No Content"),
    `205_Reset_Content`(205, "Reset Content"),
    `206_Partial_Content`(206, "Partial Content"),
    `207_Multi-Status`(207, "Multi-Status"),
    `208_Already_Reported`(208, "Already Reported"),
    `226_IM_Used`(226, "IM Used"),

    `3xx_Redirection`(3, "Redirection"),
    `300_Multiple_Choices`(300, "Multiple Choices"),
    `301_Moved_Permanently`(301, "Moved Permanently"),
    `302_Found`(302, "Found"),
    `303_See_Other`(303, "See Other"),
    `304_Not_Modified`(304, "Not Modified"),
    `305_Use_Proxy`(305, "Use Proxy"),
    `306_Switch_Proxy`(306, "Switch Proxy"),
    `307_Temporary_Redirect`(307, "Temporary Redirect"),
    `308_Permanent_Redirect`(308, "Permanent Redirect"),

    `4xx_Client_error`(4, "Client error"),
    `400_Bad_Request`(400, "Bad Request"),
    `401_Unauthorized`(401, "Unauthorized"),
    `402_Payment_Required`(402, "Payment Required"),
    `403_Forbidden`(403, "Forbidden"),
    `404_Not_Found`(404, "Not Found"),
    `405_Method_Not_Allowed`(405, "Method Not Allowed"),
    `406_Not_Acceptable`(406, "Not Acceptable"),
    `407_Proxy_Authentication_Required`(407, "Proxy Authentication Required"),
    `408_Request_Timeout`(408, "Request Timeout"),
    `409_Conflict`(409, "Conflict"),
    `410_Gone`(410, "Gone"),
    `411_Length_Required`(411, "Length Required"),
    `412_Precondition_Failed`(412, "Precondition Failed"),
    `413_Payload_Too_Large`(413, "Payload Too Large"),
    `414_URI_Too_Long`(414, "URI Too Long"),
    `415_Unsupported_Media_Type`(415, "Unsupported Media Type"),
    `416_Range_Not_Satisfiable`(416, "Range Not Satisfiable"),
    `417_Expectation_Failed`(417, "Expectation Failed"),
    `418_I_am_a_teapot`(418, "I'm a teapot"),
    `421_Misdirected_Request`(421, "Misdirected Request"),
    `422_Unprocessable_Entity`(422, "Unprocessable Entity"),
    `423_Locked`(423, "Locked"),
    `424_Failed_Dependency`(424, "Failed Dependency"),
    `425_Too_Early`(425, "Too Early"),
    `426_Upgrade_Required`(426, "Upgrade Required"),
    `428_Precondition_Required`(428, "Precondition Required"),
    `429_Too_Many_Requests`(429, "Too Many Requests"),
    `431_Request_Header_Fields_Too_Large`(431, "Request Header Fields Too Large"),
    `451_Unavailable_For_Legal_Reasons`(451, "Unavailable For Legal Reasons"),

    `5xx_Server_error`(5, "Server error"),
    `500_Internal_Server_Error`(500, "Internal Server Error"),
    `501_Not_Implemented`(501, "Not Implemented"),
    `502_Bad_Gateway`(502, "Bad Gateway"),
    `503_Service_Unavailable`(503, "Service Unavailable"),
    `504_Gateway_Timeout`(504, "Gateway Timeout"),
    `505_HTTP_Version_Not_Supported`(505, "HTTP Version Not Supported"),
    `506_Variant_Also_Negotiates`(506, "Variant Also Negotiates"),
    `507_Insufficient_Storage`(507, "Insufficient Storage"),
    `508_Loop_Detected`(508, "Loop Detected"),
    `509_Not_Extended`(509, "Not Extended"),
    `510_Gone`(510, "Gone"),
    `511_Network_Authentication_Required`(511, "Network Authentication Required");

    fun toStatus() = Result.Status(code, message)
}

infix fun Result.Status.toBe(expected: HttpStatus): Result.Status {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(this.code.toString().startsWith(expected.code.toString()), expected.toStatus())
    }
    return statusAssertion(this == expected.toStatus(), expected.toStatus())
}

infix fun Result.Status.toBeNot(expected: HttpStatus): Result.Status {
    @Suppress("MagicNumber")
    if (expected.code <= 5) {
        return statusAssertion(!this.code.toString().startsWith(expected.code.toString()), expected.toStatus())
    }
    return statusAssertion(this != expected.toStatus(), expected.toStatus())
}
