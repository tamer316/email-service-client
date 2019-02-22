package dev.choppers.email.client

import dev.choppers.email.client.model.EmailProtocol.Email
import org.specs2.mutable.Specification
import play.api.mvc.{Action, Results}
import play.api.routing.sird._
import play.api.test.WsTestClient
import play.core.server.{Server, ServerConfig}

import scala.concurrent.Await
import scala.concurrent.duration._

class EmailClientIntegrationSpec extends Specification {

  val emailTemplate = new EmailTemplate {
    override def emailText = emails.txt.test()

    override def recipient = "tester@email.com"

    override def emailHtml = emails.html.test()

    override def subject = "Test Subject"
  }

  "sendEmail" should {
    "send email" in {
      Server.withRouter(ServerConfig(address = "localhost", port = Some(8090))) {
        case POST(p"/email") => Action { req =>
          val emailReq = req.body.asJson.get.as[Email]
          emailReq.subject mustEqual "Test Subject"
          emailReq.recipient.address mustEqual "tester@email.com"
          emailReq.source.address mustEqual "test@email.com"
          emailReq.source.personal mustEqual Some("Test")
          emailReq.html mustEqual "<h1>Welcome <a href=\"#\">Tester</a>!</h1>"
          emailReq.text mustEqual "Welcome Tester!"
          emailReq.replyTo mustEqual None
          Results.Ok
        }
      } { implicit port =>
        WsTestClient.withClient { client =>
          val result = Await.result(
            new EmailClient(client).sendEmail(emailTemplate), 10 seconds)
          result mustEqual ({})
        }
      }
    }
  }
}