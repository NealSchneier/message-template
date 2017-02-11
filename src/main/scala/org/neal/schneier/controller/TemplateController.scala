package org.neal.schneier.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
  * Created by neal.schneier on 2/11/17.
  */
class TemplateController extends Controller{

  get("template") {request: Request =>
    response.ok()
  }

  post("template") {request: Request =>
    response.ok()
  }

  put("template") {request: Request =>
    response.ok()
  }

  delete("template") {request: Request =>
    response.ok()
  }
}
