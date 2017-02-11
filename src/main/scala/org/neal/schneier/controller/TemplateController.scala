package org.neal.schneier.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import org.neal.schneier.model.Template

/**
  * Created by neal.schneier on 2/11/17.
  */
class TemplateController extends Controller{

  val templates = scala.collection.mutable.Map[Int, Template]().empty

  /**
    * This is a simple get on the template with a particular Id.
    * Both in the event the id is not an Int or is not in the Templates then it's value will be 0.
    * If 0 - return 204
    * else - return 200 with the template
    */
  get("template/:id") {request: Request =>
    templates.getOrElse(request.getIntParam("id"), 0) match {
      case 0 => response.noContent
      case _ => response.ok(_)
    }
  }

  /**
    * This returns the full list of the templates with Ids.
    */
  get("templates") {request: Request =>
    response.ok(templates)
  }

  /**
    * This is used to create a template. Return is the full template, including Id.
    */
  post("template") {request: Request =>
    response.ok()
  }

  /**
    * This is to update an already existing template. The full template is updated. Only thing that remains the same
    * is the id.
    */
  put("template/:id") {request: Template =>
    response.ok(templates.+=((request.id, request)))
  }

  /**
    * This is to delete a template that already exists.
    */
  delete("template/:id") {request: Request =>
    response.ok(templates.-(request.getIntParam("id")))
  }
}
