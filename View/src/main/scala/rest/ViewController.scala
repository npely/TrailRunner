package rest

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.RequestContext
import model.levelComponent.levelBaseImpl.Level

object ViewController {

  def startGame(levelNumber: Long): Level = ???

  def move(direction: String): Level = ???

  def getCurrentLevel(): Long = 1

  def save(): Boolean = ???

  def load(): Level = ???
}
