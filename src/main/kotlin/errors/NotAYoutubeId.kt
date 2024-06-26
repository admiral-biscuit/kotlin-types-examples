package errors

@JvmInline
value class NotAYoutubeId(private val string: String) : GeneralError {
  override val message: String
    get() = "\"$string\" is not a Youtube ID"
}
