package com.lv.adviser

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LVQAHomePageSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://lv-qa-fs-sea-athenacm-aps.azurewebsites.net")
		.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "none",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map("Origin" -> "https://lv-qa-fs-sea-athenacm-aps.azurewebsites.net")

	val headers_7 = Map(
		"accept" -> "*/*",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9",
		"sec-fetch-dest" -> "script",
		"sec-fetch-mode" -> "no-cors",
		"sec-fetch-site" -> "cross-site")

	val headers_8 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Origin" -> "https://lv-qa-fs-sea-athenacm-aps.azurewebsites.net",
		"Sec-Fetch-Dest" -> "font",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "same-origin")

    val uri2 = "https://www.googletagmanager.com/gtm.js"

	val scn = scenario("LVQAHomePageSimulation")
		.exec(http("request_0")
			.get("/athena/home")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/-/prefix/media/athenadev/life-header-banner-1440x645.jpg?cx=0.66&amp;cy=0.46&amp;cw=1440&amp;ch=320&amp;hash=24A4192DDACC4100DF7BC776485D88D2EFD8DA47"),
            http("request_2")
			.get("/athenadist/fonts/Livvic-Regular.ttf")
			.headers(headers_2),
            http("request_3")
			.get("/athenadist/fonts/Livvic-SemiBold.ttf")
			.headers(headers_2),
            http("request_4")
			.get("/athenadist/fonts/Muli-Regular.ttf")
			.headers(headers_2),
            http("request_5")
			.get("/athenadist/fonts/Muli-Light.ttf")
			.headers(headers_2),
            http("request_6")
			.get("/athenadist/fonts/Muli-SemiBold.ttf")
			.headers(headers_2),
            http("request_7")
			.get(uri2 + "?id=")
			.headers(headers_7)
			.check(status.is(400)),
            http("request_8")
			.get("/athenadist/fonts/Muli-Regular.woff")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_9")
			.get("/athenadist/fonts/Livvic-SemiBold.woff")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_10")
			.get("/athenadist/fonts/Muli-SemiBold.woff")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_11")
			.get("/athenadist/fonts/Livvic-Regular.woff")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_12")
			.get("/athenadist/fonts/Muli-Regular.woff2")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_13")
			.get("/athenadist/fonts/Muli-Light.woff")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_14")
			.get("/athenadist/fonts/Livvic-SemiBold.woff2")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_15")
			.get("/athenadist/fonts/Muli-SemiBold.woff2")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_16")
			.get("/athenadist/fonts/Livvic-Regular.woff2")
			.headers(headers_8)
			.check(status.is(404)),
            http("request_17")
			.get("/athenadist/fonts/Muli-Light.woff2")
			.headers(headers_8)
			.check(status.is(404))))

	/*** setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol) ***/
  
	setUp(
	scn.inject(
		nothingFor(5 seconds),
		rampUsers(10) during (10 seconds))
	)
    .protocols(httpProtocol)
    .maxDuration(60 seconds)
	.assertions(
		global.responseTime.max.lt(5000),
		global.successfulRequests.percent.gt(80)
	)
}