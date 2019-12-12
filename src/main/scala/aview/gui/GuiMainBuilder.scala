/*package aview.gui

import controller.Controller

import scala.swing.{Action, BorderPanel, Dialog, Menu, MenuBar, MenuItem}

class GuiMainBuilder(controller: Controller, gui: GUI) {

  def getPanel: BorderPanel = {
    new BorderPanel{
      val menuBar: MenuBar = buildMenuBar()

      add(menuBar, BorderPanel.Position.North)
    }
  }

  def buildMenuBar(): MenuBar = {
    new MenuBar {
      contents += new Menu("Files") {
        contents += new MenuItem(Action("Save") {
          Dialog.showMessage(null, "Not yet implemented", ": (")
        })
        contents += new MenuItem(Action("Load") {
          Dialog.showMessage(null, "Not yet implemented", ": (")
        })
      }
      contents += new Menu("Options") {
        contents += new MenuItem(Action("Undo") {
          controller.undo
          gui.updateGame()
        })
        contents += new MenuItem(Action("Redo") {
          controller.redo
          gui.updateGame()
        })
      }
      contents += new Menu("Help") {
        contents += new MenuItem(Action("") {
          Dialog.showMessage(null, "Not yet implemented", ": (")
        })
      }
    }
  }

}*/
