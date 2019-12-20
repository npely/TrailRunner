package aview.gui

import controller.Controller
import model.maps._

import scala.swing.event.{ButtonClicked, Key}
import scala.swing.{Action, BorderPanel, BoxPanel, Button, Dimension, Menu, MenuBar, MenuItem, Orientation}

class LevelSelectionBuilder(controller: Controller, gui: GUI) {
  var selectedListIndex = 2



  def menuBar = new MenuBar {
    background = java.awt.Color.DARK_GRAY

    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Main menu") {
        //gui.changeToMainMenu()
        controller.changeToMain()
      })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
   }

  private def buildLevelSelectionPanel(): BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(500,300)
    background = java.awt.Color.BLACK
    contents ++= List(
      new Button("Level 1") {
        reactions += {
          case e: ButtonClicked => initializeGame(new Level1)
        }
      },
      new Button("Level 2") {
        reactions += {
          case e: ButtonClicked => initializeGame(new Level2)
        }
      },
      new Button("Level 3") {
        reactions += {
          case e: ButtonClicked => initializeGame(new Level3)
        }
      }
    )
  }

  def initializeGame(level: Level): Unit = {
    controller.level = level
    controller.player = controller.level.player
    controller.playerStandsOnField()
    controller.changeToGame()
  }

  /*var btnList: List[Button]

  def getLevelList: List[Button] = {
    for (e <- controller.getImplementedLevels) {
      val level = new Button(controller.levelGetName()) {
        reactions += {
          case e: ButtonClicked => //TODO
        }
        btnList.appended(level)
    }
  }*/

  def getPanel(): BorderPanel = {
    new BorderPanel {
      add(menuBar, BorderPanel.Position.North)
      add(buildLevelSelectionPanel, BorderPanel.Position.Center)
    }
  }
}
