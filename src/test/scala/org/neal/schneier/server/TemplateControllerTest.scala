package org.neal.schneier.server

import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import com.twitter.finagle.http.Status._

/**
  * Created by neal.schneier on 2/12/17.
  */
class TemplateControllerTest  extends FeatureTest{
  override protected val server: EmbeddedHttpServer = new EmbeddedHttpServer(new MessageTemplateServer)

  "Message Template Server" should {
    "startup" in {
      server.assertHealthy()
    }
    "get on empty templates" in {
      server.httpGet(path = "/templates", andExpect = Ok, withBody = "{}")
    }
    "get on non existent template" in {
      server.httpGet(path = "/template/1234", andExpect = NoContent, withBody = "")
    }
    "delete on non existent templates" in {
      server.httpDelete(path = "/template/1234", andExpect = Ok, withBody = "{}")
    }
    "create template on put" in {
      server.httpPut(path = "/template/1234", andExpect  = Ok, putBody = "{\n    \"message\": \"qwer\"\n}")
    }
    "create template on post" in {
      server.httpPost(path = "/template", andExpect  = Ok, postBody = "{\n    \"message\": \"create\"\n}")
    }
    "update template on put" in {
      server.httpPut(path = "/template/1234", andExpect  = Ok, putBody = "{\n    \"message\": \"updated\"\n}")
    }
    "get on multiple templates" in {
      server.httpGet(path = "/templates", andExpect = Ok)
    }
    "get on specific template" in {
      server.httpGet(path = "/template/1234", andExpect = Ok)
    }
    "delete a template" in {
      server.httpDelete(path = "/template/1234", andExpect = Ok)
    }
    "post to use a message that doesn't exist" in {
      server.httpPost(path = "/message/1234", postBody = "", andExpect = Ok)
    }
    "post to use a message that exists" in {
      server.httpPut("/template/1234", putBody = "{\n    \"message\": \"Disclaimer: The contents of this blurb to investors are subject to the terms and conditions outlined on [our_website_URL]. Please contact [our_legal_rep] for any further questions.\"\n}")
      server.httpPost(path = "/message/1234", postBody = "{\n    \"replacement\": {\n    \"our_website_URL\": \"http://my.website.com\",\n    \"our_legal_rep\": \"The Rock\"\n    }\n}", andExpect = Ok, withBody = "Disclaimer: The contents of this blurb to investors are subject to the terms and conditions outlined on http://my.website.com. Please contact The Rock for any further questions.")
    }
    "post to use a message without all the replacements" in {
      server.httpPut("/template/1234", putBody = "{\n    \"message\": \"Disclaimer: The contents of this blurb to investors are subject to the terms and conditions outlined on [our_website_URL]. Please contact [our_legal_rep] for any further questions.\"\n}")
      server.httpPost(path = "/message/1234", postBody = "{\n    \"replacement\": {\n    \"our_website_URL\": \"http://my.website.com\"\n    }\n}", andExpect = Ok, withBody = "Disclaimer: The contents of this blurb to investors are subject to the terms and conditions outlined on http://my.website.com. Please contact [our_legal_rep] for any further questions.")
    }
  }
}
