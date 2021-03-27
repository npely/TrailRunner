package aview.gui

import java.awt.Dimension

import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.event.ButtonClicked
import scala.swing.{BorderPanel, BoxPanel, Button, Dimension, Orientation}

class MainMenuBuilder(controller: ControllerInterface, gui: GUI) {
  var selectedListIndex = 1

  var newGameBtn = new Button("New Game") {
    reactions += {
      case e: ButtonClicked => {
        controller.changeToSelection()
      }
    }
  }

  var loadGameBtn = new Button("Load Game") {
    reactions += {
      case e: ButtonClicked => {
        controller.load(null, true)
      }
    }
  }

  var quitBtn = new Button("Quit") {
    reactions += {
      case e: ButtonClicked => System.exit(0)
    }
  }

  def mainMenu = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(500,300)
    background = java.awt.Color.BLACK
    contents ++= List(newGameBtn, loadGameBtn, quitBtn)
  }


  def getPanel(): BorderPanel = {
    new BorderPanel {
      add(mainMenu, BorderPanel.Position.Center)
    }
  }

}
