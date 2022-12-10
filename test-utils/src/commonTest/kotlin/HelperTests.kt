import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class HelperTests : FunSpec() {

    init {

        extension(DockerExtension)

        test("Will run if docker available").config(enabledOrReasonIf = DockerExtension.isAvailable) {
            DockerExtension.isAvailable(this.testCase).isEnabled.shouldBeTrue()
        }

        test("Will run if docker not available").config(enabledOrReasonIf = DockerExtension.isNotAvailable) {
            DockerExtension.isAvailable(this.testCase).isEnabled.shouldBeFalse()
        }
    }

}