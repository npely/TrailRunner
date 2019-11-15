package controller

import model.Player
import util.Observable

class Controller(player: Player) extends Observable {

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  def playerMoveUp: Unit = {
    player.moveUp()
    notifyObservers
  }

  def playerMoveDown: Unit = {
    player.moveDown()
    notifyObservers
  }

  def playerMoveRight: Unit = {
    player.moveDown()
    notifyObservers
  }

  def playerMoveLeft: Unit = {
    player.moveLeft()
    notifyObservers
  }
}
