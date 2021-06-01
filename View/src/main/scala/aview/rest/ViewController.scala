package aview.rest

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCode}
import model.levelComponent.levelBaseImpl.Level
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.unmarshalling.Unmarshal
import config.ModelJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import controller.controllerBaseImpl.Controller

import scala.concurrent.duration.DurationInt
import scala.util.{Try, Failure, Success}
import scala.concurrent.{Await, Future}

object ViewController {

  val persistenceApiBaseUrl: String = "http://persistence:8080/"
  val levelApiBaseUrl: String = "http://level:8080/"

  implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext = system.executionContext

  val controller = new Controller

  def startGame(levelId: Long): Option[Level] = {
    val level = controller.start(levelId).asInstanceOf[Level]
    println(level)
    Some(level)
  }

  def move(direction: String): Option[Level] = {
    direction match {
      case "up" => controller.playerMove(direction)(controller.level)(controller.player)(() => controller.player.moveUp(), () => controller.player.moveDown())
      case "down" => controller.playerMove(direction)(controller.level)(controller.player)(() => controller.player.moveDown(), () => controller.player.moveUp())
      case "left" => controller.playerMove(direction)(controller.level)(controller.player)(() => controller.player.moveLeft(), () => controller.player.moveRight())
      case "right" => controller.playerMove(direction)(controller.level)(controller.player)(() => controller.player.moveRight(), () => controller.player.moveLeft())
      case "undo" => controller.undo
      case "redo" => controller.redo
      case _ => return None
    }
    println(controller.level.asInstanceOf[Level])
    Some(controller.level.asInstanceOf[Level])
  }

  def getCurrentLevel(): Long = 1

  def save(): Boolean = {

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      uri = persistenceApiBaseUrl + "save",
      method = HttpMethods.POST,
      entity = HttpEntity(ContentTypes.`application/json`, controller.level.asInstanceOf[Level].toJson.toString())
    ))

    responseFuture.onComplete {
      case Success(response) => {
        if (response.status.equals(StatusCode.int2StatusCode(200))) {
          println("save request was successful")
        } else {
          sys.error("save request responsed with status-code: " + response.status)
        }
      }
      case Failure(e) => {
        sys.error("save request failed: " + e.getMessage)
      }
    }
    true
  }

  def load(): Option[Level] = {
    Try (Unmarshal(Await.result(Http().singleRequest(HttpRequest(
      uri = persistenceApiBaseUrl + "load")),
      5.seconds)).to[Level].value.get.get) match {
      case Success(level) => {
        Some(level)
      }
      case Failure(e) => {
        sys.error("load request failed: " + e.getMessage)
        None
      }
    }
  }

  def delete(): Boolean = {

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      uri = persistenceApiBaseUrl + "delete"
    ))

    responseFuture.onComplete {
      case Success(response) => {
        if (response.status.equals(StatusCode.int2StatusCode(200))) {
          true
        } else {
          sys.error("save request responses with status-code: " + response.status)
          false
        }
      }
      case Failure(e) => {
        sys.error("delete request failed: " + e.getMessage)
        false
      }
    }
    true
  }
}
