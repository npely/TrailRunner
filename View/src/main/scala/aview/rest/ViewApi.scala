package rest

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import config.ModelJsonProtocol._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpResponse, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.http.scaladsl.unmarshalling.Unmarshal
import model.levelComponent.levelBaseImpl.Level
import spray.json.JsNumber

import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success}

object ViewApi {

  def main(args: Array[String]): Unit = {
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
        (get & path("level" / "id")) {
          complete(JsNumber(ViewController.getCurrentLevel()).toString())
        },
        (post & path("level" / "start" / LongNumber)) { levelNumber =>
            complete(ViewController.startGame(levelNumber))
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
        (post & path("player" / "up")) {
          complete(ViewController.move("up"))
        },
        (post & path("player" / "down")) {
          complete(ViewController.move("down"))
        },
        (post & path("player" / "right")) {
          complete(ViewController.move("right"))
        },
        (post & path("player" / "left")) {
          complete(ViewController.move("left"))
        },
        (post & path("player" / "undo")) {
          complete(ViewController.move("undo"))
        },
        (post & path("player" / "redo")) {
          complete(ViewController.move("redo"))
        },
      )
    )

    val bindingFuture = Http().newServerAt("localhost", 8082).bind(route)
    println(s"View server online at http://localhost:8082/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
