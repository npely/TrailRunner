package controller

import controller.MoveCommands.{MoveDownCommand, MoveLeftCommand, MoveRightCommand, MoveUpCommand}
import model.{AllLevels, Field}
import model.maps.Level
import model.player.Player
import util.{Observable, UndoManager}



class Controller(var player: Player, var field: Field, var level: Level) extends Observable {

  private val undoManager = new UndoManager

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  var counter: Int = 0

  def playerMoveUp(): Unit = {
    undoManager.doStep(new MoveUpCommand(this))
    notifyObservers()
  }

  def playerMoveDown(): Unit = {
    undoManager.doStep(new MoveDownCommand(this))
    notifyObservers()
  }

  def playerMoveRight(): Unit = {
    undoManager.doStep(new MoveRightCommand(this))
    notifyObservers()
  }

  def playerMoveLeft(): Unit = {
    undoManager.doStep(new MoveLeftCommand(this))
    notifyObservers()
  }

  def fieldIsBroken:Boolean = field.isBroken

  def fieldIsSet:Boolean = field.isSet

  def playerStandsOnField():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.PlayerStandsOnField()
  }

  def increaseFieldValueByOne():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.setValue(field.value + 1)
  }

  def count: Int = {
    counter += 1
    notifyObservers()
    counter
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers()
  }

  def fieldToString: String = field.toString

  def levelToString: String = level.toString

  def levelWin():Boolean = level.win()

  def levelLose():Boolean = level.lose()

  def showLevel(level: Level): String = AllLevels.showLevel(level)

  def getImplementedLevels: List[Level] = AllLevels.getImplementedList()
}
