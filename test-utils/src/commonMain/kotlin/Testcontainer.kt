import io.ktor.client.*

expect object Testcontainer {

    class Wiremock {
        val httpUrl: String
        val httpsUrl: String
        val ktorClient: HttpClient
    }

    suspend fun getWiremock(): Wiremock
    suspend fun getHttpBin(): String
}