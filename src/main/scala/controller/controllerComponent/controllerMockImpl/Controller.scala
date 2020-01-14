package controller.controllerComponent.controllerMockImpl

import controller.controllerComponent.ControllerInterface
import model.AllLevels
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.{Level, Level1}
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}

import scala.swing.Publisher

class Controller() extends ControllerInterface with Publisher {

  var level: LevelInterface = new Level1

  var field: FieldInterface = Field(0)

  var player: PlayerInterface = PlayerFactory.createPlayer1()

  override def playerToString: String = player.toString

  override def playerToGameString: String = player.toGameString

  override def changeToSelection(): Unit = {}

  override def changeToGame(): Unit = {}

  override def changeToMain(): Unit = {}

  override def playerMoveUp(): Unit = {}

  override def playerMoveDown(): Unit = {}

  override def playerMoveRight(): Unit = {}

  override def playerMoveLeft(): Unit = {}

  override def fieldIsBroken: Boolean = false

  override def fieldIsSet: Boolean = false

  override def playerStandsOnField(): Unit = {}

  override def increaseFieldValueByOne(): Unit = {}

  override def count: Int = 1

  override def undo: Unit = {}

  override def redo: Unit = {}

  override def fieldToString: String = field.toString

  override def levelToString: String = level.toString

  override def levelWin(): Boolean = false

  override def levelLose(): Boolean = false

  override def levelGetName(): String = level.getName

  override def showLevel(level: LevelInterface): String = AllLevels.showLevel(level)

  override def getImplementedLevels: List[LevelInterface] = List(level)
}
