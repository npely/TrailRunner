package rest

import akka.http.scaladsl.model.StatusCode
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import model.fieldComponent.fieldBaseImpl.Field
import spray.json.{JsNumber, RootJsonFormat}

import scala.io.StdIn

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
        (post & path("level" / LongNumber)) { levelNumber =>
          val success = ViewController.startGame(levelNumber)
          if (success) {
            complete(StatusCode.int2StatusCode(200))
          } else {
            complete(StatusCode.int2StatusCode(400))
          }
        },
        (get & path("level")) {
            complete(JsNumber(ViewController.getCurrentLevel()).toString())
        }
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
