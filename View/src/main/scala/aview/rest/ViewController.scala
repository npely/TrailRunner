package aview.rest

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
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}
import scala.io.{BufferedSource, Source}

object ViewController {

  def startGame(levelNumber: Long): Level = ???

  def move(direction: String): Level = ???

  def getCurrentLevel(): Long = 1

  def save(): Boolean = ???

  def load(): Option[Level] = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext

    Try (Unmarshal(Await.result(Http().singleRequest(HttpRequest(
        uri = "http://localhost:8080/load")),
        5.seconds)).to[Level].value.get.get) match {
      case Success(level) => {
        Some(level)
      }
      case Failure(e) => {
        println("load request failed: " + e.getMessage)
        None
      }
    }
  }
}
