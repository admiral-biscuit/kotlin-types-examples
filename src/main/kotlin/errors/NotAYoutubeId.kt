package errors

data class NotAYoutubeId(private val string: String) : GeneralError {
  override val message: String
    get() = "\"$string\" is not a valid Youtube ID"
}
