package controller

import controller.MoveCommands.{MoveDownCommand, MoveLeftCommand, MoveRightCommand, MoveUpCommand}
import de.htwg.se.sudoku.controller.DungeonChanged
import model.maps.Level
import model.player.Player
import model.{AllLevels, Field}
import util.UndoManager

import scala.swing.Publisher



class Controller(var player: Player, var field: Field, var level: Level) extends Publisher {

  private val undoManager = new UndoManager

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  var counter: Int = 0

  def playerMoveUp(): Unit = {
    undoManager.doStep(new MoveUpCommand(this))
    publish(new DungeonChanged)
  }

  def playerMoveDown(): Unit = {
    undoManager.doStep(new MoveDownCommand(this))
    publish(new DungeonChanged)
  }

  def playerMoveRight(): Unit = {
    undoManager.doStep(new MoveRightCommand(this))
    publish(new DungeonChanged)
  }

  def playerMoveLeft(): Unit = {
    undoManager.doStep(new MoveLeftCommand(this))
    publish(new DungeonChanged)
  }

  def fieldIsBroken:Boolean = field.isBroken

  def fieldIsSet:Boolean = field.isSet

  def playerStandsOnField():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.PlayerStandsOnField()
    field.isPlayerOnField = true
  }

  def increaseFieldValueByOne():Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.setValue(field.value + 1)
  }

  def count: Int = {
    counter += 1
    publish(new DungeonChanged)
    counter
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new DungeonChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new DungeonChanged)
  }

  def fieldToString: String = field.toString

  def levelToString: String = level.toString

  def levelWin():Boolean = level.win()

  def levelLose():Boolean = level.lose()

  def levelGetName(): String = level.getName

  def showLevel(level: Level): String = AllLevels.showLevel(level)

  def getImplementedLevels: List[Level] = AllLevels.getImplementedList()
}
