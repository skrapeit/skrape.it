import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.await

actual enum class Platform(actual val value:Boolean) {
    JVM(false),
    JS(true),
    WEB(js(
        "typeof window !== 'undefined' && typeof window.document !== 'undefined' || typeof self !== 'undefined' && typeof self.location !== 'undefined'" // ktlint-disable max-line-length
    ) as Boolean),
    NODE(js(
        "typeof process !== 'undefined' && process.versions != null && process.versions.node != null"
    ) as Boolean),
    WINDOWS(NODE.value && js("process.platfrom == \"win32\"") as Boolean),
    LINUX(NODE.value && js("process.platfrom == \"linux\"") as Boolean);
}

actual suspend fun testDockerAvailable(): Boolean {
    return if (Platform.NODE.value) {
        println("Trying to start node container")
        try {
            val container = GenericContainer("alpine").start().await()
            container.stop()
            true
        } catch (e: Throwable) {
            println("Caught exception $e")
            false
        }
    } else if (Platform.WEB.value) {
        println("Testing container availability")
        val resp = Testcontainer.client.post("http://localhost:9876/containers/available")
        println("Status was ${resp.status}")
        resp.status == HttpStatusCode.OK
    } else {
        error("Unknown JS Version")
    }
}