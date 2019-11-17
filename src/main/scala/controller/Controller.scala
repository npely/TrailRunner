package controller

import model.{Field, Player}
import util.Observable

class Controller(player: Player, field: Field) extends Observable {


  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  def playerMoveUp(): Unit = {
    player.moveUp()
    notifyObservers
  }

  def playerMoveDown(): Unit = {
    player.moveDown()
    notifyObservers
  }

  def playerMoveRight(): Unit = {
    player.moveDown()
    notifyObservers
  }

  def playerMoveLeft(): Unit = {
    player.moveLeft()
    notifyObservers
  }

  def fieldIsBroken:Boolean = {
    notifyObservers
    field.value == 0
  }

  def fieldIsSet:Boolean = field.value >= 0

  def PlayerStandsOnField():Unit = {
    field.value = field.value - 1
    notifyObservers
  }

  def fieldToString: String = " |" + field.value.toString + "| "
}
