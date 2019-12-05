package controller

import model.{AllLevels, Field}
import model.maps.Level
import model.player.Player
import util.Observable



class Controller(var player: Player, var field: Field, var level: Level) extends Observable {

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  var counter: Int = 0

  def playerMoveUp(): Unit = {
    player.moveUp()
    playerStandsOnField
    notifyObservers()
  }

  def playerMoveDown(): Unit = {
    player.moveDown()
    playerStandsOnField
    notifyObservers()
  }

  def playerMoveRight(): Unit = {
    player.moveRight()
    playerStandsOnField
    notifyObservers()
  }

  def playerMoveLeft(): Unit = {
    player.moveLeft()
    playerStandsOnField
    notifyObservers()
  }

  def fieldIsBroken:Boolean = field.isBroken

  def fieldIsSet:Boolean = field.isSet

  def playerStandsOnField():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.PlayerStandsOnField()
  }

  def count: Int = {
    counter += 1
    notifyObservers()
    counter
  }

  def fieldToString: String = field.toString

  def levelToString: String = level.toString

  def levelWin():Boolean = level.win()

  def levelLose():Boolean = level.lose()

  def showLevel(level: Level): String = AllLevels.showLevel(level)

  def getImplementedLevels: List[Level] = AllLevels.getImplementedList()
}
