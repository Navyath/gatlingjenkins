package finalSimulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SampleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://gatling.io")
    .inferHtmlResources()
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,kn-IN;q=0.8,kn;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Accept" -> "*/*",
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "script",
    "Sec-Fetch-Mode" -> "no-cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_2 = Map(
    "Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "no-cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_3 = Map(
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "no-cors",
    "Sec-Fetch-Site" -> "none")



  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get("/gatling-frontline/demo/")
      .headers(headers_0)
      .resources(http("request_1")
        .get("/wp-content/plugins/wp-rocket/inc/front/js/lazyload-10.17.min.js")
        .headers(headers_1),
        http("request_2")
          .get("/favicon.ico")
          .headers(headers_2),
        http("request_3")
          .get("/wp-content/plugins/wp-rocket/inc/front/js/lazyload-10.17.min.js.map")
          .headers(headers_3)))

 /*** setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)***/
  
   setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(100) during (10 seconds))
  )
    .protocols(httpProtocol)
    .maxDuration(60 seconds)
      .assertions(
        global.responseTime.max.lt(5000),
        global.successfulRequests.percent.gt(95)
      )

}