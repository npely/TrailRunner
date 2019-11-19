package controller

import model.{Field, Player, AllLevels}
import model.maps.Level
import util.Observable



class Controller(var player: Player, var field: Field, var level: Level) extends Observable {

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  var counter: Int = 0

  def playerMoveUp(): Unit = {
    player.moveUp()
    notifyObservers()
  }

  def playerMoveDown(): Unit = {
    player.moveDown()
    notifyObservers()
  }

  def playerMoveRight(): Unit = {
    player.moveRight()
    notifyObservers()
  }

  def playerMoveLeft(): Unit = {
    player.moveLeft()
    notifyObservers()
  }

  def fieldIsBroken:Boolean = field.isBroken

  def fieldIsSet:Boolean = field.isSet

  def playerStandsOnField():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.PlayerStandsOnField()
    notifyObservers()
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
