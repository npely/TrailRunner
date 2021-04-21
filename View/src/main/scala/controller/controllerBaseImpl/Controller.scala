package controller.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import controller.controllerBaseImpl.MoveCommands._
import controller._
import main.TrailRunnerModule
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player
import play.api.libs.json.JsValue
import util.UndoManager

import scala.swing.Publisher

class Controller @Inject() extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TrailRunnerModule)

  var level: LevelInterface = Level("Level1", Player (4, 5), 5, 4, 5, 3, false, 10)

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

  def playerMove(direction: String)(level: LevelInterface)(player: PlayerInterface)(forwardStep: () => PlayerInterface, backwardStep: () => PlayerInterface): Boolean = {
    makePlayerMove(direction)(level)(player)(forwardStep, backwardStep)(undoManager)
  }

  private def makePlayerMove(direction: String)(level: LevelInterface)(player: PlayerInterface)(forwardStep: () => PlayerInterface, backwardStep: () => PlayerInterface)(undoManager: UndoManager): Boolean = {
    if (checkIfMoveLegal(level)(player)(direction)) {
      undoManager.doStep(new MoveCommand(this, forwardStep, backwardStep))
      publish(new DungeonChanged)
      changeMoveCounter(direction)
      return true
    }
    false
  }

  private def checkIfMoveLegal(level: LevelInterface)(player: PlayerInterface)(direction: String): Boolean = {
    direction match {
      case "up" => level.dungeon(player.yPos - 1)(player.xPos).value >= -1
      case "down" => level.dungeon(player.yPos + 1)(player.xPos).value >= -1
      case "left" => level.dungeon(player.yPos)(player.xPos - 1).value >= -1
      case "right" => level.dungeon(player.yPos)(player.xPos + 1).value >= -1
    }
  }

  def fieldIsBroken: Boolean = field.isBroken

  def fieldIsSet: Boolean = field.isSet

  def playerStandsOnField(): Unit = {
    this.field = level.dungeon(player.yPos)(player.xPos).PlayerWalksOnField()
    level.dungeon(player.yPos)(player.xPos) = field
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

  override def save: String = {
    //fileIO.save(level)
    ???
  }

  override def load(json: JsValue, isOldGame: Boolean): Unit = {
    //level = fileIO.load()
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
    //level = fileIO.start(name)
    player = level.player
    field = level.dungeon(player.yPos)(player.xPos)
    System.out.println("field")
    System.out.println(field)
    System.out.println("player")
    System.out.println(player)
    System.out.println("level")
    System.out.println(level)
    level
  }
}
