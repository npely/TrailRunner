package controller.controllerComponent
import com.google.inject.Injector
import fileIOComponent.FileIOInterface
import model.fieldComponent.FieldInterface
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import play.api.libs.json.{JsObject, JsValue}

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  var fileIO: FileIOInterface
  var player: PlayerInterface
  var field: FieldInterface
  var level: LevelInterface
  var hardcoreMode: Boolean
  def playerToString: String
  def playerToGameString: String
  def changeToSelection(): Unit
  def changeToGame(): Unit
  def changeToMain(): Unit
  def playerMove(direction: String)(level: LevelInterface)(player: PlayerInterface)(forwardStep: () => PlayerInterface, backwardStep: () => PlayerInterface): Boolean
  def fieldIsBroken: Boolean
  def fieldIsSet: Boolean
  def playerStandsOnField(): Unit
  def increaseFieldValueByOne(): Unit
  def initializeGame(level: LevelInterface, loaded: Boolean): Unit
  def count: Int
  def getLevelAsJson: JsObject
  def undo: Unit
  def redo: Unit
  def save: String
  def load(json: JsValue, isOldGame: Boolean): Unit
  def fieldToString: String
  def levelToString: String
  def levelWin(): Boolean
  def levelLose(): Boolean
  def levelGetName(): String
  def win(): Unit
  def lose(): Unit
  def standsPlayerInFrontOfOpenDoor(): Boolean
  def earthquake(): Unit
  def resetMoveCounter(): Unit
  def getHardcoreMode(): Boolean
  def setHardcoreMode(isHardcoreModeOn: Boolean): Unit
  def start(name: String): LevelInterface
}


import scala.swing.event.Event

class DungeonChanged extends Event
class ChangeToSelection extends Event
class ChangeToGame extends Event
class ChangeToMain extends Event
class Lose extends Event
class Win extends Event
class Earthquake extends Event
