import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class YoutubeIdTest :
    DescribeSpec({
      it("is a valid YoutubeId") {
        val subject = YoutubeId.eitherFromString("haf67eKF0uo")

        val result = subject.shouldBeRight()
        result.value shouldBe "haf67eKF0uo"
      }

      it("is not a valid YoutubeId") {
        val subject = YoutubeId.eitherFromString("garbage")

        val result = subject.shouldBeLeft()
        result.message shouldBe "\"garbage\" is not a Youtube ID"
      }
    })
