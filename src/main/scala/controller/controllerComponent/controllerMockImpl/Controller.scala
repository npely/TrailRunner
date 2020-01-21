package controller.controllerComponent.controllerMockImpl

import com.google.inject.Guice
import controller.controllerComponent.ControllerInterface
import model.AllLevels
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.{Level, Level1}
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}
import src.main.TrailRunnerModule.TrailRunnerModule
import net.codingwell.scalaguice.InjectorExtensions._

import scala.swing.Publisher

class Controller() extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new TrailRunnerModule)

  var fileIO = injector.instance[FileIOInterface]

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

  override def initializeGame(level: LevelInterface, loaded: Boolean): Unit = {}

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

  override def lose(): Unit = {}

  override def win(): Unit = {}

  override def openDoor(): Unit = {}

  override def save: Unit = {}

  override def load: Unit = {}
}
