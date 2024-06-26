package errors

// This is the base interface that every error should implement.
// For simplicity, we assume that the only attribute an error should have is an error message.

interface GeneralError {
  val message: String
}
