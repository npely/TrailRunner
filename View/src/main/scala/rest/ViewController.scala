package rest

import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.RequestContext
import akka.http.scaladsl.unmarshalling.Unmarshal
import model.levelComponent.levelBaseImpl.Level
import config.ModelJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.concurrent.Future

object ViewController {

  def startGame(levelNumber: Long): Level = ???

  def move(direction: String): Level = ???

  def getCurrentLevel(): Long = 1

  def save(): Boolean = ???

  def load(): Level = {
    print(Get("http://localhost:8080/load").isResponse())
    print(Get("http://localhost:8081/load").entity)
    null
  }
}
