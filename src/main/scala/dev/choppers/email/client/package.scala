package dev.choppers.email

package object client {
  case class EmailServiceException(message: String) extends Exception(message)
}
