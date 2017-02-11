package org.neal.schneier.server

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.StatsFilter
import com.twitter.finatra.http.routing.HttpRouter
import org.neal.schneier.controller.TemplateController

/**
  * Created by neal.schneier on 2/11/17.
  */
object MessageTemplateServerMain extends MessageTemplateServer

class MessageTemplateServer extends HttpServer{

  override protected def configureHttp(router: HttpRouter){
    router
      .filter[StatsFilter[Request]]
      .add[TemplateController]
  }
}
