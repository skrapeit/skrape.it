import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

external fun require(string: String): dynamic

val client = HttpClient(Js)
val fs = require("fs")

actual suspend fun getMarkupFromFile(location: String): String = getInputFromFile(location).readText()

actual suspend fun getInputFromFile(location: String): Input {
    return if (+Platform.WEB) {
        //In the browser karma is set up to serve our files
        client.get("http://localhost:9876/__files/$location").body()
    } else {
        //When running on node use fs
        val projectPath = js("process.env[\"PROJECT_PATH\"]")
        val buff = fs.readFileSync("$projectPath/build/processedResources/js/combined/__files/$location")
        //Copy from nodes Buffer objet to ByteArray
        val byteArray = ByteArray(buff.length as Int) { i -> buff[i] as Byte }
        return ByteReadPacket(byteArray.unsafeCast<ByteArray>())
    }
}