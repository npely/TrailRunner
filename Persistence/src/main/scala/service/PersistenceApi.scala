package service

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import config.ModelJsonProtocol._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCode
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import model.levelComponent.levelBaseImpl.Level

import scala.io.StdIn

object PersistenceApi {

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
        (get & path("load")) {
          complete(PersistenceController.loadLastScore())
        },
        (post & path("save")) {
          entity(as[Level]) { level =>
            val success = PersistenceController.saveLastScore(level)
            if (success) {
              complete(StatusCode.int2StatusCode(200))
            } else {
              complete(StatusCode.int2StatusCode(500))
            }
          }
        }
      )
    )

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println(s"Persistence server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
