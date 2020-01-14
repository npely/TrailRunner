package controller.controllerComponent.controllerBaseImpl

import com.google.inject.Inject
import controller.controllerComponent.{ChangeToGame, ChangeToMain, ChangeToSelection, ControllerInterface, DungeonChanged}
import controller.controllerComponent.controllerBaseImpl.MoveCommands._
import model.levelComponent.levelBaseImpl.{Level, Level1}
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}
import model.AllLevels
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import util.UndoManager

import scala.swing.Publisher



//class Controller @Inject() (var player: PlayerInterface, var field: FieldInterface, var level: LevelInterface) extends ControllerInterface with Publisher {
class Controller @Inject() () extends ControllerInterface with Publisher {

  var level: LevelInterface = new Level1

  var field: FieldInterface = Field(0)

  var player: PlayerInterface = PlayerFactory.createPlayer1()

  private val undoManager = new UndoManager

  def playerToString: String = player.toString

  def playerToGameString: String = player.toGameString

  var counter: Int = 0

  def changeToSelection(): Unit = {
    publish(new ChangeToSelection)
  }

  def changeToGame(): Unit = {
    publish(new ChangeToGame)
  }

  def changeToMain(): Unit = {
    publish(new ChangeToMain)
  }

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

  def showLevel(level: LevelInterface): String = AllLevels.showLevel(level)

  def getImplementedLevels: List[LevelInterface] = AllLevels.getImplementedList()
}
