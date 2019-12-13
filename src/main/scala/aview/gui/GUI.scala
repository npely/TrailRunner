package aview.gui

import controller.Controller
import de.htwg.se.sudoku.controller.DungeonChanged

import scala.swing._
import scala.swing.event.Key


class GUI(controller: Controller) extends Frame{

  listenTo(controller)

  title = "TrailRunner"
  preferredSize = new Dimension(600,400)
  centerOnScreen()

  contents = mainMenuPanel

  visible = true

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

  def changeToLevelSelection(): Unit = {
    contents = levelSelectionPanel()
  }

  def changeToMainMenu(): Unit = {
    contents = mainMenuPanel()
  }

  def changeToRunningGame(): Unit = {
    contents = gamePanel()
  }

  reactions += {
    case event: DungeonChanged => redraw
  }

  def redraw = {
    repaint
  }
}
