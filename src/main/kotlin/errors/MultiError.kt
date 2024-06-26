package errors

import arrow.core.NonEmptyList

interface MultiError<E : GeneralError> : GeneralError {
  val errors: NonEmptyList<E>

  override val message: String
    get() = errors.joinToString { "- $message\n" }
}
