/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TrailRunnerSimulationLevel3 extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("User starts game, makes some moves and saves (loads)")
    .exec(
      http("retrieve level id")
        .get("/level/id")
    )
    .pause(2)
    .exec(
      http("start level 3")
        .post("/level/start/3")
    )
    .pause(2)
    .exec(
      http("player deletes the game save")
        .post("/level/delete")
    )
    .pause(2)
    .exec(
      http("player saves the game")
        .post("/level/save")
    )
    .pause(5)
    .exec(
      http("player load the game")
        .get("/level/load")
    )
    .pause(5)
    .exec(
      http("player moves right")
        .post("/player/right")
    )
    .pause(2)
    .exec(
      http("player moves right")
        .post("/player/right")
    )
    .pause(2)
    .exec(
      http("player moves right")
        .post("/player/right")
    )
    .pause(2)
    .exec(
      http("player moves up")
        .post("/player/up")
    )
    .pause(2)
    .exec(
      http("player moves left")
        .post("/player/left")
    )
    .pause(2)
    .exec(
      http("player moves right")
        .post("/player/right")
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
