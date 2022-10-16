@file:Suppress("MagicNumber")

import com.benasher44.uuid.uuid4
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.*

private suspend fun Testcontainer.Wiremock.baseStubWithFile(
    method: String = "get",
    path: String = "/",
    statusCode: Int = 200,
    contentType: String = "text/html; charset=UTF-8",
    fileName: String = "example.html",
    delay: Int = 0
) {
    ktorClient.post {
        url("$httpUrl/__admin/mappings")
        contentType(ContentType.Application.Json)
        setBody(buildJsonObject {
            putJsonObject("request") {
                put("method", method.uppercase())
                put("url", path)
            }
            putJsonObject("response") {
                putJsonObject("headers") {
                    put("Content-Type", contentType)
                }
                put("status", statusCode)
                put("fixedDelayMilliseconds", delay)
                put("bodyFileName", fileName)
            }
        })
    }
}

private suspend fun Testcontainer.Wiremock.stubMethod(
    method: String
) {
    ktorClient.post {
        url("$httpUrl/__admin/mappings")
        setBody(buildJsonObject {
            putJsonObject("request") {
                put("method", method)
                put("url", "/")
            }
            putJsonObject("response") {
                put("status", 201)
                put("body", "i'm a ${method.uppercase()} stub")
            }
        })
    }
}

suspend fun Testcontainer.Wiremock.setupStub(
    path: String = "/",
    statusCode: Int = 200,
    contentType: String = "text/html; charset=UTF-8",
    fileName: String = "example.html",
    delay: Int = 0
) = baseStubWithFile(
    method = "get",
    path = path,
    statusCode = statusCode,
    contentType = contentType,
    fileName = fileName,
    delay = delay
)

suspend fun Testcontainer.Wiremock.setupPostStub() = baseStubWithFile(
    method = "post",
    contentType = "application/json; charset=UTF-8",
    fileName = "data.json"
)

suspend fun Testcontainer.Wiremock.setupRedirect() {
    ktorClient.post {
        url("$httpUrl/__admin/mappings")
        setBody(buildJsonObject {
            putJsonObject("request") {
                put("method", "get")
                put("url", "/")
            }
            putJsonObject("response") {
                put("status", 403)
                putJsonObject("headers") {
                    put("Content-Type", "text/html")
                    put("location", "/redirected")
                }
                put("body", uuid4().toString())
            }
        })
    }
}

suspend fun Testcontainer.Wiremock.setupPutStub() = stubMethod("put")

suspend fun Testcontainer.Wiremock.setupDeleteStub() = stubMethod("delete")

suspend fun Testcontainer.Wiremock.setupPatchStub() = stubMethod("patch")

suspend fun Testcontainer.Wiremock.setupHeadStub() = stubMethod("head")

suspend fun Testcontainer.Wiremock.setupCookiesStub(
    path: String = "/"
) {
    ktorClient.post {
        url("$httpUrl/__admin/mappings")
        setBody(buildJsonObject {
            putJsonObject("request") {
                put("method", "get")
                put("url", path)
            }
            putJsonObject("response") {
                put("status", 200)
                putJsonObject("headers") {
                    putJsonArray("Set-Cookie") {
                        add("basic=value")
                        add("advanced=advancedValue; Domain=localhost; Path=$path; Secure; HttpOnly; SameSite=Strict")
                        add("expireTest=value; Expires=Wed, 21 Oct 2015 07:28:00 GMT; Max-Age=2592000")
                    }
                }
                put("body", uuid4().toString())
            }
        })
    }
}
