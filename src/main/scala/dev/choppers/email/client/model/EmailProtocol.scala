package dev.choppers.email.client.model

import play.api.libs.json.{Format, Json}

object EmailProtocol {

  case class InternetAddress(address: String, personal: Option[String] = None, charset: Option[String] = None)

  case class Email(source: InternetAddress, recipient: InternetAddress, subject: String, html: String,
                   text: String, replyTo: Option[InternetAddress] = None)

  implicit val internetAddressFormat: Format[InternetAddress] = Json.format[InternetAddress]
  implicit val emailFormat: Format[Email] = Json.format[Email]
}