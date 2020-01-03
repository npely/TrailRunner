package controller.controllerComponent
import model.levelComponent.levelBaseImpl.Level

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

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
  def showLevel(level: Level): String
  def getImplementedLevels: List[Level]

}


import scala.swing.event.Event

class DungeonChanged extends Event
class ChangeToSelection extends Event
class ChangeToGame extends Event
class ChangeToMain extends Event
