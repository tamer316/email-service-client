package dev.choppers.email.client

import play.twirl.api.{HtmlFormat, TxtFormat}

trait EmailTemplate {
  def subject: String

  def recipient: String

  def emailHtml: HtmlFormat.Appendable

  def htmlAsString = emailHtml.toString()

  def emailText: TxtFormat.Appendable

  def textAsString = emailText.toString()
}
