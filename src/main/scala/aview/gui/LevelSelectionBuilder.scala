package aview.gui

import controller.Controller

import scala.swing.event.{ButtonClicked, Key}
import scala.swing.{Action, BorderPanel, BoxPanel, Button, Dimension, Menu, MenuBar, MenuItem, Orientation}

class LevelSelectionBuilder(controller: Controller, gui: GUI) {
  var selectedListIndex = 2



  def menuBar = new MenuBar {

    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Main menu") { gui.changeToMainMenu() })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
   }

  private def buildLevelSelectionPanel(): BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(70,50)
    contents ++= List(
      new Button("Level 1") {
        reactions += {
          case e: ButtonClicked => gui.changeToRunningGame()
        }
      },
      new Button("Level 2") {
        reactions += {
          case e: ButtonClicked => gui.changeToRunningGame()
        }
      },
      new Button("Level 3") {
        reactions += {
          case e: ButtonClicked => gui.changeToRunningGame()
        }
      }
    )
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
