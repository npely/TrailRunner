package persistence.service

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

  val routes: String =
    """
        Welcome to the persistence-service! Available routes:

          GET   /load
          POST  /save
        """.stripMargin

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
        (get & path("")) {
          complete(routes)
        },
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

    Http().newServerAt("0.0.0.0", 8080).bind(route)
    println(s"Persistence server online at http://persistence:8080/\nPress RETURN to stop...")
  }
}
