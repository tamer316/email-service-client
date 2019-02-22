package dev.choppers.email.client

import javax.inject.{Inject, Singleton}

import com.typesafe.config.ConfigFactory
import dev.choppers.email.client.model.EmailProtocol.{Email, InternetAddress}
import grizzled.slf4j.Logging
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class EmailClient @Inject()(ws: WSClient) extends Logging {
  val config = ConfigFactory.load

  val source = InternetAddress(address = config.getString("mail.source.address"),
    personal = Some(config.getString("mail.source.personal")))

  val emailServiceUrl = config.getString("mail.service.url")

  def sendEmail(emailTemplate: EmailTemplate): Future[Unit] = {
    val email = Email(source, InternetAddress(emailTemplate.recipient),
      emailTemplate.subject, emailTemplate.htmlAsString,
      emailTemplate.textAsString)

    ws.url(emailServiceUrl + "/email").post(Json.toJson(email)).flatMap {
      response => {
        response.status match {
          case OK => Future.successful({})
          case x => {
            error(s"Received Status $x from Server, response: ${response.body}")
            Future.failed(EmailServiceException(s"Received Status $x from Server"))
          }
        }
      }
    }
  }
}
