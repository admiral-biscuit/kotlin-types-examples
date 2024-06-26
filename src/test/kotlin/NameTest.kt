import errors.NameError
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.assertions.arrow.core.shouldHaveSize
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class NameTest :
    DescribeSpec({
      it("is a name") {
        val subject = Name.eitherFromString("Biscuit")

        val name = subject.shouldBeRight()
        name.value shouldBe "Biscuit"
      }

      describe("error cases") {
        it("is blank") {
          val subject = Name.eitherFromString("")

          subject shouldBeLeft NameError.BlankName
        }

        it("is too long") {
          val subject = Name.eitherFromString("A".repeat(100))

          val error = subject.shouldBeLeft()
          error.shouldBeInstanceOf<NameError.TooLong>()
        }

        it("contains a slur") {
          val subject = Name.eitherFromString("Pikachu69")

          val error = subject.shouldBeLeft()
          error.shouldBeInstanceOf<NameError.ContainsSlur>()
        }

        it("is loo long and contains a slur") {
          val subject = Name.eitherFromString("pikachu".repeat(10))

          val error = subject.shouldBeLeft()
          val errors = error.shouldBeInstanceOf<NameError.MultipleIssues>().errors

          errors shouldHaveSize 2
          errors[0].shouldBeInstanceOf<NameError.TooLong>()
          errors[1].shouldBeInstanceOf<NameError.ContainsSlur>().slur shouldBe "Pikachu"
        }

        it("contains multiple slurs") {
          val subject = Name.eitherFromString("Pikachu_Diggersby")

          val error = subject.shouldBeLeft()
          val errors = error.shouldBeInstanceOf<NameError.MultipleIssues>().errors

          errors shouldHaveSize 2
          errors[0].shouldBeInstanceOf<NameError.ContainsSlur>().slur shouldBe "Pikachu"
          errors[1].shouldBeInstanceOf<NameError.ContainsSlur>().slur shouldBe "Diggersby"
        }
      }
    })
