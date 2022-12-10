import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.await

internal actual val supportedPlatforms: List<Platform> by lazy {
    val platforms = mutableListOf(Platform.JS)
    val isWeb = js(
        "typeof window !== 'undefined' && typeof window.document !== 'undefined' || typeof self !== 'undefined' && typeof self.location !== 'undefined'" // ktlint-disable max-line-length
    ) as Boolean
    val isNode = js(
        "typeof process !== 'undefined' && process.versions != null && process.versions.node != null"
    ) as Boolean
    if (isNode) {
        platforms.add(Platform.NODE)
        if (js("process.platform == \"win32\"") as Boolean)
            platforms.add(Platform.WINDOWS)
        else if (js("process.platform == \"linux\"") as Boolean)
            platforms.add(Platform.LINUX)
    } else if (isWeb) {
        platforms.add(Platform.WEB)
    } else {
        error("Unsupported JS platform")
    }
    platforms
}

actual suspend fun testDockerAvailable(): Boolean {
    return if (+Platform.NODE) {
        try {
            GenericContainer("alpine").start().await().stop().await()
            true
        } catch (e: Throwable) {
            false
        }
    } else if (+Platform.WEB) {
        val resp = Testcontainer.client.post("http://localhost:9876/containers/available")
        println("Status was ${resp.status}")
        resp.status == HttpStatusCode.OK
    } else {
        error("Unknown JS Version")
    }
}