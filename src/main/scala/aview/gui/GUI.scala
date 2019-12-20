package aview.gui

import controller.Controller
import de.htwg.se.sudoku.controller.DungeonChanged

import scala.swing._


class GUI(controller: Controller) extends MainFrame{

  listenTo(controller)

  title = "TrailRunner"
  minimumSize = new Dimension(550,630)
  preferredSize = new Dimension(550,630)
  maximumSize = new Dimension(550,630)
  background = java.awt.Color.BLACK

  contents = mainMenuPanel

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
    case event: DungeonChanged => //redraw
  }

  def redraw = {
    repaint
  }
}
