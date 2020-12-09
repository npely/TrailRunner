package controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import controller.controllerComponent.{ChangeToGame, ChangeToMain, ChangeToSelection, ControllerInterface, DungeonChanged, Lose, Win}
import controller.controllerComponent.controllerBaseImpl.MoveCommands._
import main.TrailRunnerModule
import model.levelComponent.levelBaseImpl.{Level, Level1}
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}
import model.AllLevels
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import util.UndoManager
import model.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.JsObject

import scala.swing.Publisher

class Controller @Inject()() extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TrailRunnerModule)

  var fileIO: FileIOInterface = injector.instance[FileIOInterface]

  var level: LevelInterface = new Level1

  var field: FieldInterface = Field(0, "Ground")

  var player: PlayerInterface = PlayerFactory.createPlayer1()

  var hardcoreMode: Boolean = false

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

  def lose(): Unit = {
    publish(new Lose)
  }

  def win(): Unit = {
    publish(new Win)
  }

  def playerMoveUp(): Boolean = {
    if (level.dungeon(player.yPos - 1)(player.xPos).value >= -1) {
      undoManager.doStep(new MoveUpCommand(this))
      publish(new DungeonChanged)
      return true
    }
    false
  }

  def playerMoveDown(): Boolean = {
    if (level.dungeon(player.yPos + 1)(player.xPos).value >= -1) {
      undoManager.doStep(new MoveDownCommand(this))
      publish(new DungeonChanged)
      return true
    }
    false
  }

  def playerMoveRight(): Boolean = {
    if (level.dungeon(player.yPos)(player.xPos + 1).value >= -1) {
      undoManager.doStep(new MoveRightCommand(this))
      publish(new DungeonChanged)
      return true
    }
    false
  }

  def playerMoveLeft(): Boolean = {
    if (level.dungeon(player.yPos)(player.xPos - 1).value >= -1) {
      undoManager.doStep(new MoveLeftCommand(this))
      publish(new DungeonChanged)
      return true
    }
    false
  }

  def fieldIsBroken: Boolean = field.isBroken

  def fieldIsSet: Boolean = field.isSet

  def playerStandsOnField(): Unit = {
    field = level.dungeon(player.yPos)(player.xPos)
    field.PlayerStandsOnField()
    field.isPlayerOnField = true
  }

  def increaseFieldValueByOne(): Unit = {
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

  override def getLevelAsJson: JsObject = {
    fileIO.levelToJson(level)
  }

  override def save: String = {
    fileIO.save(level)
  }

  override def load(json: String, isOldGame: Boolean): Unit = {
    level = fileIO.load(json)
    initializeGame(level, isOldGame)
  }

  def initializeGame(level: LevelInterface, loaded: Boolean): Unit = {
    this.level = level
    player = level.player
    playerStandsOnField()
    if (loaded) {
      increaseFieldValueByOne()
    }
    changeToGame()
  }

  def fieldToString: String = field.toString

  def levelToString: String = level.toString

  def levelWin(): Boolean = level.win()

  def levelLose(): Boolean = level.lose()

  def levelGetName(): String = level.getName

  def showLevel(level: LevelInterface): String = AllLevels.showLevel(level)

  def getImplementedLevels: List[LevelInterface] = AllLevels.getImplementedList()

  def standsPlayerInFrontOfOpenDoor(): Boolean = level.standsPlayerInFrontOfOpenDoor()

  def earthquake(): Unit = {
    if (hardcoreMode) {
      for (i <- 0 until level.rows; j <- 0 until level.columns) {
        level.dungeon(i)(j).earthquake
      }
    }
  }
}
