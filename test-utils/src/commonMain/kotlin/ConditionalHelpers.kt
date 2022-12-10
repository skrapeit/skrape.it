import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.test.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

enum class Platform {
    JVM,
    WINDOWS,
    LINUX,
    JS,
    WEB,
    NODE;

    operator fun unaryPlus() = supportedPlatforms.contains(this)
    operator fun unaryMinus() = !unaryPlus()
}

internal expect val supportedPlatforms: List<Platform>

infix fun EnabledOrReasonIf.and(other: EnabledOrReasonIf): EnabledOrReasonIf = { tc ->
    val myRes = this(tc)
    val otherRes = other(tc)
    if (myRes.isDisabled) myRes
    else if (otherRes.isDisabled) otherRes
    else Enabled.enabled
}

infix fun EnabledOrReasonIf.or(other: EnabledOrReasonIf): EnabledOrReasonIf = { tc ->
    val myRes = this(tc)
    val otherRes = other(tc)
    if (myRes.isDisabled && otherRes.isDisabled) myRes
    else Enabled.enabled
}

fun runOnPlatform(vararg platforms: Platform): EnabledOrReasonIf = {
    platforms.fold(Enabled.disabled("Only runs on ${platforms.joinToString { it.name }}")) { acc, platform ->
        if (acc.isEnabled || +platform) {
            acc
        } else {
            Enabled.enabled
        }
    }
}

fun dontRunOnPlatform(vararg platforms: Platform): EnabledOrReasonIf = {
    platforms.fold(Enabled.enabled) { acc, platform ->
        if (acc.isDisabled || -platform) {
            acc
        } else {
            Enabled.disabled("Does not run on ${platform.name}")
        }
    }
}

//This has to be a TestCaseExtension because of kotest#3317
object DockerExtension : TestCaseExtension {

    private val mutex = Mutex()

    private lateinit var available: Enabled
    private lateinit var notAvailable: Enabled

    val isAvailable: EnabledOrReasonIf = {
        //Workaround for kotest#3317
        if (this::available.isInitialized)
            available
        else
            Enabled.enabled
    }
    val isNotAvailable: EnabledOrReasonIf = {
        //Workaround for kotest#3317
        if (this::notAvailable.isInitialized)
            notAvailable
        else
            Enabled.enabled
    }

    private suspend fun initialize() {
        mutex.withLock {
            if (!this::available.isInitialized) {
                println("Initializing docker")
                if (testDockerAvailable()) {
                    available = Enabled.enabled
                    notAvailable = Enabled.disabled("Docker environment found")
                } else {
                    available = Enabled.disabled("No docker environment found")
                    notAvailable = Enabled.enabled
                }
            }
        }
    }

    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase) -> TestResult): TestResult {
        initialize()
        val res = execute(testCase)
        //Workaround for kotest#3318
        return if (+Platform.JS && res is TestResult.Ignored) {
            println("Actual result was $res")
            TestResult.Success(Duration.ZERO)
        } else {
            res
        }
    }

    internal suspend fun isDockerAvailable(): Boolean {
        initialize()
        return available.isEnabled
    }
}

object TestcontainerExtension : TestCaseExtension {

    class TestcontainerContextElement(
        val wiremock: Testcontainer.Wiremock,
        val httpBin: String
    ) : AbstractCoroutineContextElement(TestcontainerContextElement) {
        companion object Key : CoroutineContext.Key<TestcontainerContextElement>

    }

    private lateinit var contextElement: TestcontainerContextElement

    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase) -> TestResult): TestResult {
        return if (DockerExtension.isDockerAvailable()) {
            if (!::contextElement.isInitialized) {
                contextElement = TestcontainerContextElement(
                    Testcontainer.getWiremock(),
                    Testcontainer.getHttpBin()
                )
            }
            withContext(contextElement) { execute(testCase) }
        } else {
            execute(testCase)
        }
    }

}

val TestScope.wiremock: Testcontainer.Wiremock
    get() = coroutineContext.wiremock

val CoroutineContext.wiremock: Testcontainer.Wiremock
    get() = get(TestcontainerExtension.TestcontainerContextElement)?.wiremock
        ?: error("Wiremock is not injected into this CoroutineContext")

val TestScope.httpBin: String
    get() = coroutineContext.httpBin

val CoroutineContext.httpBin: String
    get() = get(TestcontainerExtension.TestcontainerContextElement)?.httpBin
        ?: error("HttpBin is not injected into this CoroutineContext")

expect suspend fun testDockerAvailable(): Boolean