import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class YoutubeIdTest :
    DescribeSpec({
      it("is a valid YoutubeId") {
        val subject = YoutubeId.eitherFromString("haf67eKF0uo")

        val youtubeId = subject.shouldBeRight()
        youtubeId.value shouldBe "haf67eKF0uo"
      }

      it("is not a valid YoutubeId") {
        val subject = YoutubeId.eitherFromString("garbage")

        val error = subject.shouldBeLeft()
        error.message shouldBe "\"garbage\" is not a Youtube ID"
      }
    })
