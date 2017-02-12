package org.neal.schneier.controller

import java.util.regex.Pattern
import javax.inject.{Inject, Singleton}

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import org.neal.schneier.model.{TemplateMessage, RequestTemplate, Template}
import org.neal.schneier.service.TemplateService

/**
  * Created by neal.schneier on 2/11/17.
  */
@Singleton
class TemplateController @Inject()(templateService: TemplateService) extends Controller{

  val templates = scala.collection.mutable.Map[Int, Template]().empty

  /**
    * This is a simple get on the template with a particular Id.
    * Both in the event the id is not an Int or is not in the Templates then it's value will be 0.
    * If 0 - return 204
    * else - return 200 with the template
    */
  get("/template/:id") {request: Request =>
    templates.getOrElse(request.getIntParam("id"), 0) match {
      case 0 => response.noContent
      case temp: Template => response.ok(temp)
    }
  }

  /**
    * This returns the full list of the templates with Ids.
    */
  get("/templates") {request: Request =>
    response.ok(templates)
  }

  /**
    * This is used to create a template. Return is the full template, including Id.
    */
  post("/template") {request: Template =>
    val id = templates.size + 1
    response.ok(templates.+=((id, Template(request.message))))
  }

  /**
    * This is to update an already existing template. The full template is updated. Only thing that remains the same
    * is the id.
    */
  put("/template/:id") {request: RequestTemplate =>
    response.ok(templates.+=((request.id, Template(request.message))))
  }

  /**
    * This is to delete a template that already exists.
    */
  delete("/template/:id") {request: Request =>
    response.ok(templates.-(request.getIntParam("id")))
  }

  /**
    * Post to receive a templated message. The Post body contains the actual template placeholder values
    */
  post("/message/:id") { request: TemplateMessage =>
    response.ok(replacePlaceholders(templates.getOrElse[Template](request.id, None[Template]).message, request.replacement))
  }

  /**
    * This function takes in the message stored and the replacements included in the request and makes
    * the necessary substitutions to generate the legal message required.
    * Assumption - the request map that is posted on the request includes the name of the string in the template
    *
    * @param message
    * @param replacements
    * @return
    */
  def replacePlaceholders(message: String, replacements: Map[String, String]): String = {
    var response = message
    replacements.foreach[String](x => {
      response = response.replaceFirst(Pattern.quote("[" + x._1 + "]"), x._2)
      response
    })
    response
  }
}
