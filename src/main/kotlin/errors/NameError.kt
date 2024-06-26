package errors

import arrow.core.NonEmptyList

sealed interface NameError : GeneralError {
  data object BlankName : NameError {
    override val message: String
      get() = "Name must not be empty."
  }

  data class TooLong(val name: String, val maxLength: Int) : NameError {
    override val message: String
      get() =
          "Name \"$name\" (${name.length} characters) exceeds maximum name length of $maxLength characters."
  }

  data class ContainsSlur(val name: String, val slur: String) : NameError {
    override val message: String
      get() = "Name \"$name\" must not contain \"$slur\"."
  }

  data class MultipleIssues(override val errors: NonEmptyList<NameError>) :
      NameError, MultiError<NameError>
}
