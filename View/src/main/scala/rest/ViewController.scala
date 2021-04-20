package rest

import akka.http.scaladsl.model.HttpRequest
import model.levelComponent.levelBaseImpl.Level
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.directives.MarshallingDirectives.as
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.unmarshalling.Unmarshal
import config.ModelJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.ExceptionHandler

import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.{Failure, Success}
import scala.concurrent.{Await, Future}

object ViewController {

  def startGame(levelNumber: Long): Level = ???

  def move(direction: String): Level = ???

  def getCurrentLevel(): Long = 1

  def save(): Boolean = ???

  def load(): Option[Level] = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val req = Get("http://localhost:8080/load")
    val responseFuture: Future[HttpResponse] = Http().singleRequest(req)

    var optLevel: Option[Level] = None
    var futureLevel: Future[Level] = null

    responseFuture
      .onComplete {
        case Success(res) => {
          futureLevel = Unmarshal(res).to[Level]
          futureLevel.onComplete({
            case Success(level) => {
              println("hello im a level")
              optLevel = Some(level)
              println(optLevel.isEmpty)
            }
            case Failure(exception) => {
              println(exception.getMessage)
            }
          })
        }
        case Failure(_)  => println("load request failed")
      }
    Await.result(responseFuture, Duration.Inf)
    Await.result(futureLevel, Duration.Inf)
    optLevel
  }
}
