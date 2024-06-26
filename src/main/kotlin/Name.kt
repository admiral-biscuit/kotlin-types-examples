import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.core.toNonEmptyListOrNull
import errors.NameError

@JvmInline
value class Name private constructor(val value: String) {
  companion object {
    // A name may not be longer than 64 characters.
    private const val MAX_LENGTH = 64

    // A name may not contain these prohibited substrings. (Case will be ignored.)
    private val SLURS = listOf("Pikachu", "Diggersby")

    fun eitherFromString(string: String): Either<NameError, Name> {
      val errors = mutableListOf<NameError>()

      if (string.isBlank()) {
        errors.add(NameError.BlankName)
      }

      if (string.length > MAX_LENGTH) {
        errors.add(NameError.TooLong(string, MAX_LENGTH))
      }

      SLURS.forEach { slur ->
        if (string.lowercase().contains(slur.lowercase())) {
          errors.add(NameError.ContainsSlur(string, slur))
        }
      }

      return if (errors.isEmpty()) {
        Name(string).right()
      } else {
        when (errors.size) {
          1 -> errors.single()
          else -> NameError.MultipleIssues(errors.toNonEmptyListOrNull()!!)
        }.left()
      }
    }
  }
}

fun String.toNameEither(): Either<NameError, Name> = Name.eitherFromString(this)
