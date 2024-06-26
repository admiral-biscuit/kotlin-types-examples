import arrow.core.Either
import arrow.core.left
import arrow.core.right
import errors.NotAYoutubeId

@JvmInline
value class YoutubeId private constructor(val value: String) {
  companion object {
    private val REGEX = Regex("[A-Za-z0-9_\\-]{11}")

    fun eitherFromString(string: String): Either<NotAYoutubeId, YoutubeId> =
        if (string.matches(REGEX)) {
          YoutubeId(string).right()
        } else {
          NotAYoutubeId(string).left()
        }
  }
}

fun String.toYoutubeIdEither(): Either<NotAYoutubeId, YoutubeId> = YoutubeId.eitherFromString(this)
