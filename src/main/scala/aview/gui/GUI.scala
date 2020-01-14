package aview.gui

import java.io.File

import javax.imageio.ImageIO
import controller.controllerComponent.{ChangeToGame, ChangeToMain, ChangeToSelection, ControllerInterface, DungeonChanged, Lose, Win}

import scala.swing._


class GUI(controller: ControllerInterface) extends MainFrame{

  listenTo(controller)

  title = "TrailRunner"
  iconImage = ImageIO.read(new File("src/main/scala/aview/gui/images/TR.png"))
  minimumSize = new Dimension(550,630)
  preferredSize = new Dimension(550,630)
  maximumSize = new Dimension(550,630)
  background = java.awt.Color.BLACK

  contents = mainMenuPanel()

  visible = true
  centerOnScreen()

  def mainMenuPanel(): BorderPanel = {
    val mainMenuBuilder = new MainMenuBuilder(controller, this)
    mainMenuBuilder.getPanel()
  }

  def levelSelectionPanel(): BorderPanel = {
    val levelSelectionBuilder = new LevelSelectionBuilder(controller, this)
    levelSelectionBuilder.getPanel()
  }

  def gamePanel(): BorderPanel = {
    val gameBuilder = new GameBuilder(controller, this)
    gameBuilder.getPanel()
  }

  def losePanel(): BorderPanel = {
    val mainMenuBuilder = new MainMenuBuilder(controller, this)
    mainMenuBuilder.getPanel()
  }

  def winPanel(): BorderPanel = {
    val mainMenuBuilder = new MainMenuBuilder(controller, this)
    mainMenuBuilder.getPanel()
  }

  def changeToLevelSelection(): Unit = {
    contents = levelSelectionPanel()
  }

  def changeToMainMenu(): Unit = {
    contents = mainMenuPanel()
  }

  def changeToRunningGame(): Unit = {
    contents = gamePanel()
  }

  def lose(): Unit = {
    contents = losePanel()
  }

  def win(): Unit = {
    contents = winPanel()
  }

  reactions += {
    //case _: DungeonChanged => //redraw
    case _: ChangeToGame => changeToRunningGame()
    case _: ChangeToSelection => changeToLevelSelection()
    case _: ChangeToMain => changeToMainMenu()
    case _: Lose => changeToMainMenu()
    case _: Win => changeToMainMenu()
    //case _: OpenDoor => //redraw
  }

}
