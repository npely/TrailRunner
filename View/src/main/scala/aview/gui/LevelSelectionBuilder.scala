package aview.gui

import controller.ControllerInterface
import model.levelComponent._
import model.levelComponent.levelBaseImpl.Level

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
          case e: ButtonClicked => controller.start(1)
        }
      },
      new Button("Level 2") {
        reactions += {
          case e: ButtonClicked => controller.start(2)
        }
      },
      new Button("Level 3") {
        reactions += {
          case e: ButtonClicked => controller.start(3)
        }
      },
      new Button("Level 4") {
        reactions += {
          case e: ButtonClicked => controller.start(4)
        }
      },
      new Button("Level 5") {
        reactions += {
          case e: ButtonClicked => controller.start(5)
        }
      },
      new Button("Level 6") {
        reactions += {
          case e: ButtonClicked => controller.start(6)
        }
      },
      new Button("Level 7") {
        reactions += {
          case e: ButtonClicked => controller.start(7)
        }
      }
    )
  }

  def getPanel(): BorderPanel = {
    new BorderPanel {
      add(menuBar, BorderPanel.Position.North)
      add(buildLevelSelectionPanel, BorderPanel.Position.Center)
    }
  }
}
