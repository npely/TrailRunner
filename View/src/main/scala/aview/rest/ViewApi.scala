package aview.rest

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import config.ModelJsonProtocol._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpResponse, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route, StandardRoute}
import model.levelComponent.levelBaseImpl.Level
import spray.json.JsNumber

import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success}

object ViewApi {

  val routes: String =
    """
        Welcome to the view-service! Available routes:

          GET   /level/id
          GET   /level/load
          POST  /level/save
          POST  /level/start/{id}
          POST  /player/up
          POST  /player/down
          POST  /player/right
          POST  /player/left
          POST  /player/undo
          POST  /player/redo

        """.stripMargin

  private def getMoveResponse(direction: String): StandardRoute = {
    val option = ViewController.move(direction)
    if(option.isDefined) {
      complete(option.get)
    } else {
      complete(direction + " is not a valid move command")
    }
  }

  def start(): Unit = {
    // needed to run the route
    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    implicit def myExceptionHandler: ExceptionHandler =
      ExceptionHandler {
        case e: Exception =>
          println("---------------- exception log start")
          e.printStackTrace()
          println("---------------- exception log end")
          complete("server made a boo boo")
      }

    val route = Route.seal(
      concat(
        (get & path("")) {
          complete(routes)
        },
        (get & path("level" / "id")) {
          complete(JsNumber(ViewController.getCurrentLevel()).toString())
        },
        (post & path("level" / "start" / LongNumber)) { levelNumber =>
          val level = ViewController.startGame(levelNumber)
          if (level.isDefined) {
            complete(level.get)
          } else {
            complete(StatusCode.int2StatusCode(500))
          }
        },
        (post & path("level" / "save")) {
          val success = ViewController.save()
          if (success) {
            complete(StatusCode.int2StatusCode(200))
          } else {
            complete(StatusCode.int2StatusCode(400))
          }
        },
        (get & path("level" / "load")) {
          val level = ViewController.load()
          if (level.isDefined) {
            complete(level.get)
          } else {
            complete(StatusCode.int2StatusCode(500))
          }
        },
        (post & path("level" / "delete")) {
          val success = ViewController.delete()
          if (success) {
            complete(StatusCode.int2StatusCode(200))
          } else {
            complete(StatusCode.int2StatusCode(400))
          }
        },
        (post & path("player" / Segment)) { direction: String =>
          getMoveResponse(direction)
        }
      )
    )

    Http().newServerAt("0.0.0.0", 8080).bind(route)
    println(s"View server online at http://localhost:8080/\nPress RETURN to stop...")
  }
}
