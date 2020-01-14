package controller.controllerComponent
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  var player: PlayerInterface
  var level: LevelInterface
  def playerToString: String
  def playerToGameString: String
  def changeToSelection(): Unit
  def changeToGame(): Unit
  def changeToMain(): Unit
  def playerMoveUp(): Unit
  def playerMoveDown(): Unit
  def playerMoveRight(): Unit
  def playerMoveLeft(): Unit
  def fieldIsBroken: Boolean
  def fieldIsSet: Boolean
  def playerStandsOnField(): Unit
  def increaseFieldValueByOne(): Unit
  def count: Int
  def undo: Unit
  def redo: Unit
  def fieldToString: String
  def levelToString: String
  def levelWin(): Boolean
  def levelLose(): Boolean
  def levelGetName(): String
  def showLevel(level: LevelInterface): String
  def getImplementedLevels: List[LevelInterface]

}


import scala.swing.event.Event

class DungeonChanged extends Event
class ChangeToSelection extends Event
class ChangeToGame extends Event
class ChangeToMain extends Event
