package aview.rest

import akka.http.scaladsl.model.{HttpRequest, StatusCode}
import model.levelComponent.levelBaseImpl.Level
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.unmarshalling.Unmarshal
import config.ModelJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import controller.controllerBaseImpl.Controller

import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}

object ViewController {

  val persistenceApiBaseUrl: String = "http://persistence:8080/"
  val levelApiBaseUrl: String = "http://level:8080/"

  implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext = system.executionContext

  val controller = new Controller

  def startGame(levelId: Long): Option[Level] = {
    Try (Unmarshal(Await.result(Http().singleRequest(HttpRequest(
      uri = "%slevel/%d".format(levelApiBaseUrl, levelId))),
      5.seconds)).to[Level].value.get.get) match {
      case Success(level) => {
        controller.initializeGame(level, false)
        Some(level)
      }
      case Failure(e) => {
        sys.error("level request failed: " + e.getMessage)
        None
      }
    }
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
    Some(controller.level.asInstanceOf[Level])
  }

  def getCurrentLevel(): Long = 1

  def save(): Boolean = {
    Try (Await.result(Http().singleRequest(HttpRequest(
      uri = persistenceApiBaseUrl + "save")),
      5.seconds).status) match {
      case Success(status) => {
        if (status.equals(StatusCode.int2StatusCode(200))) {
          true
        } else {
          sys.error("save request responses with status-code: " + status)
          false
        }
      }
      case Failure(e) => {
        sys.error("save request failed: " + e.getMessage)
        false
      }
    }
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
}
