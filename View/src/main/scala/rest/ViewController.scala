package rest

import model.levelComponent.levelBaseImpl.Level

object ViewController {

  def startGame(levelNumber: Long): Level = ???

  def move(direction: String): Level = ???

  def getCurrentLevel(): Long = 1

  def save(): Boolean = ???

  def load(): Level = {
    ???
  }
}
