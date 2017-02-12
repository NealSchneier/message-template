package org.neal.schneier.server

import com.google.inject.Stage
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import org.scalatest.FunSpec

/**
  * Created by neal.schneier on 2/12/17.
  */
class TemplateControllerTest  extends FeatureTest{
  override protected val server: EmbeddedHttpServer = new EmbeddedHttpServer(new MessageTemplateServer)

  "Message Template Server" should {
    "startup" in {
      server.assertHealthy()
    }
  }
}
