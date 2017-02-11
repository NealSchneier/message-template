package org.neal.schneier.model

import com.twitter.finatra.request.RouteParam

/**
  * Id will be the id.
  * When stored the message will contain the message.
  * Should templates have names?
  */
case class RequestTemplate(@RouteParam id: Int, message: String)

/**
  * The actual object to store
  * @param message
  */
case class Template(message: String)

/**
  * The template request object. Since it is possible for there to be no replacements on the message.
  */
case class TemplateMessage(@RouteParam id: Int, replacement: Map[String, String] = Map.empty)