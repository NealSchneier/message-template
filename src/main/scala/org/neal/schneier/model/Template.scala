package org.neal.schneier.model

import com.twitter.finatra.request.RouteParam

/**
  * Id will be the id.
  * When stored the message will contain the message.
  * Should templates have names?
  */
case class Template(@RouteParam id: Int, message: String)
