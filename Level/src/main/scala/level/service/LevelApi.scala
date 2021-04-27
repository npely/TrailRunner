package level.service

import akka.http.scaladsl.server.Directives.{complete, path, get, concat}
import config.ModelJsonProtocol._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import scala.io.StdIn

object LevelApi {

  val routes: String =
    """
        Welcome to the level-service! Available routes:

          GET   /level/{id}
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
        (get & path("level" / LongNumber)) { id =>
          complete(LevelController.createLevel(id))
        }
      )
    )

    Http().newServerAt("0.0.0.0", 8080).bind(route)
    println(s"Level server online at http://level:8080/\nPress RETURN to stop...")
  }
}
