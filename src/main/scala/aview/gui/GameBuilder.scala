package aview.gui

import controller.Controller

import scala.swing.event.Key
import scala.swing.{Action, BorderPanel, Menu, MenuBar, MenuItem}

class GameBuilder(controller: Controller, gui: GUI) {
  var selectedListIndex = 3

  def menuBar = new MenuBar {

    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Main menu") { gui.changeToMainMenu() })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }

    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
  }

  def getPanel(): BorderPanel = {
    new BorderPanel {
      add(menuBar, BorderPanel.Position.North)
    }
  }
}
