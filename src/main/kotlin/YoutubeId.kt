import arrow.core.Either
import arrow.core.left
import arrow.core.right
import errors.NotAYoutubeId

@JvmInline
value class YoutubeId private constructor(val value: String) {
  companion object {
    private val REGEX = Regex("[A-Za-z0-9_\\-]{11}")

    // non-functional, Exception-based approach
    fun fromString(string: String): YoutubeId =
        if (string.matches(REGEX)) {
          YoutubeId(string)
        } else {
          throw IllegalArgumentException("\"$string\" is not a valid Youtube ID")
        }

    // functional, Either-based approach
    fun eitherFromString(string: String): Either<NotAYoutubeId, YoutubeId> =
        if (string.matches(REGEX)) {
          YoutubeId(string).right()
        } else {
          NotAYoutubeId(string).left()
        }
  }

  fun url(): String = "https://www.youtube.com/watch?v=$value"
}

fun String.toYoutubeId(): YoutubeId = YoutubeId.fromString(this)

fun String.toYoutubeIdEither(): Either<NotAYoutubeId, YoutubeId> = YoutubeId.eitherFromString(this)
