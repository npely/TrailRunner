package controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import controller.controllerComponent.{ChangeToGame, ChangeToMain, ChangeToSelection, ControllerInterface, DungeonChanged, Earthquake, Lose, Win}
import controller.controllerComponent.controllerBaseImpl.MoveCommands._
import main.TrailRunnerModule
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import util.UndoManager
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent.fileIO_Json_Impl.FileIO
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.{JsObject, JsValue}

import scala.swing.Publisher

class Controller @Inject() extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TrailRunnerModule)

  var fileIO: FileIOInterface = injector.instance[FileIOInterface]

  var level: LevelInterface = null

  var field: FieldInterface = Field(0, "Ground", false, false)

  var player: PlayerInterface = Player(0, 0)

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

  object moveCounter {
    var typeOfMove = ""
    var count = 0
  }

  def resetMoveCounter(): Unit = {
    moveCounter.typeOfMove = ""
    moveCounter.count = 0
  }

  def changeMoveCounter(direction: String): Unit = {
    if (hardcoreMode) {
      if (moveCounter.typeOfMove == direction) {
        moveCounter.count += 1
        if (moveCounter.count == 3) {
          publish(new Earthquake)
          resetMoveCounter()
        }
      } else {
        moveCounter.typeOfMove = direction
        moveCounter.count = 1
      }
    }
  }

  def playerMoveUp(): Boolean = {
    if (level.dungeon(player.yPos - 1)(player.xPos).value >= -1) {
      undoManager.doStep(new MoveCommand(this, () => player.moveUp(), () => player.moveDown()))
      publish(new DungeonChanged)
      changeMoveCounter("up")
      return true
    }
    false
  }

  def playerMoveDown(): Boolean = {
    if (level.dungeon(player.yPos + 1)(player.xPos).value >= -1) {
      undoManager.doStep(new MoveCommand(this, () => player.moveDown(), () => player.moveUp()))
      publish(new DungeonChanged)
      changeMoveCounter("down")
      return true
    }
    false
  }

  def playerMoveRight(): Boolean = {
    if (level.dungeon(player.yPos)(player.xPos + 1).value >= -1) {
      undoManager.doStep(new MoveCommand(this, () => player.moveRight(), () => player.moveLeft()))
      publish(new DungeonChanged)
      changeMoveCounter("right")
      return true
    }
    false
  }

  def playerMoveLeft(): Boolean = {
    if (level.dungeon(player.yPos)(player.xPos - 1).value >= -1) {
      undoManager.doStep(new MoveCommand(this, () => player.moveLeft(), () => player.moveRight()))
      publish(new DungeonChanged)
      changeMoveCounter("left")
      return true
    }
    false
  }

  def fieldIsBroken: Boolean = field.isBroken

  def fieldIsSet: Boolean = field.isSet

  def playerStandsOnField(): Unit = {
    this.field = level.dungeon(player.yPos)(player.xPos).PlayerWalksOnField()
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

  override def load(json: JsValue, isOldGame: Boolean): Unit = {
    level = fileIO.load(json)
    initializeGame(level, isOldGame)
  }

  def initializeGame(level: LevelInterface, loaded: Boolean): Unit = {
    resetMoveCounter()
    hardcoreMode = false
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

  def levelGetName(): String = level.name

  def standsPlayerInFrontOfOpenDoor(): Boolean = level.standsPlayerInFrontOfOpenDoor().isDoorOpen

  def earthquake(): Unit = {
    if (hardcoreMode) {
      for (i <- 0 until level.size; j <- 0 until level.size) {
        level.dungeon(i)(j).earthquake
      }
    }
  }

  override def getHardcoreMode(): Boolean = {
    hardcoreMode
  }

  override def setHardcoreMode(isHardcoreModeOn: Boolean): Unit = {
    hardcoreMode = isHardcoreModeOn
  }

  override def start(name: String): LevelInterface = {
    fileIO.start(name)
  }
}
