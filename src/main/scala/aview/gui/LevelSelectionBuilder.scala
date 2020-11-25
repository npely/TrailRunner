package aview.gui

import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import model.levelComponent._
import model.levelComponent.levelBaseImpl.{Level, Level1, Level2, Level3, Level4, Level5}

import scala.swing.event.{ButtonClicked, Key}
import scala.swing.{Action, BorderPanel, BoxPanel, Button, Dimension, Menu, MenuBar, MenuItem, Orientation}

class LevelSelectionBuilder(controller: ControllerInterface, gui: GUI) {
  var selectedListIndex = 2


  def menuBar = new MenuBar {
    background = java.awt.Color.DARK_GRAY

    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Main menu") {
        controller.changeToMain()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  private def buildLevelSelectionPanel(): BoxPanel = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(500, 300)
    background = java.awt.Color.BLACK
    contents ++= List(
      new Button("Level 1") {
        reactions += {
          case e: ButtonClicked => controller.initializeGame(new Level1, false)
        }
      },
      new Button("Level 2") {
        reactions += {
          case e: ButtonClicked => controller.initializeGame(new Level2, false)
        }
      },
      new Button("Level 3") {
        reactions += {
          case e: ButtonClicked => controller.initializeGame(new Level3, false)
        }
      },
      new Button("Level 4") {
        reactions += {
          case e: ButtonClicked => controller.initializeGame(new Level4, false)
        }
      },
      new Button("Level 5") {
        reactions += {
          case e: ButtonClicked => controller.initializeGame(new Level5, false)
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
