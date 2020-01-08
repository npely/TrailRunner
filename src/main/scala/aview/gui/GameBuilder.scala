package aview.gui

import java.awt.Dimension

import controller.Controller

import scala.swing.event.{ButtonClicked, Key}
import scala.swing.{Action, BorderPanel, Button, Component, Dimension, FlowPanel, GridPanel, Menu, MenuBar, MenuItem}

class GameBuilder(controller: Controller, gui: GUI) {
  var selectedListIndex = 3

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

    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo })
      contents += new MenuItem(Action("Redo") {
        controller.redo })
    }
  }

  var cells: Array[Array[CellBuilder]] = Array.ofDim[CellBuilder](controller.level.rows, controller.level.columns)


  def dungeonGrid: GridPanel = new GridPanel(controller.level.rows, controller.level.columns) {
    override val size = new Dimension(550, 550)
    background = java.awt.Color.BLACK
    for (i <- 0 until controller.level.rows; j <- 0 until controller.level.columns) {
      contents += CellBuilder(i, j , controller)
    }
  }

  var upBtn = new Button("Up") {
    mnemonic = Key.W
    reactions += {
      case e: ButtonClicked => controller.playerMoveUp()
    }
  }

  var downBtn = new Button("Down") {
    mnemonic = Key.S
    reactions += {
      case e: ButtonClicked => controller.playerMoveDown()
    }
  }

  var leftBtn = new Button("Left") {
    mnemonic = Key.A
    reactions += {
      case e: ButtonClicked => controller.playerMoveLeft()
    }
  }

  var rightBtn = new Button("Right") {
    mnemonic = Key.D
    reactions += {
      case e: ButtonClicked => controller.playerMoveRight()
    }
  }

  var undoBtn = new Button("Undo") {
    mnemonic = Key.Q
    reactions += {
      case e: ButtonClicked => controller.undo
    }
  }

  var redoBtn = new Button("Redo") {
    mnemonic = Key.E
    reactions += {
      case e: ButtonClicked => controller.redo
    }
  }

  def controlPanel(): FlowPanel = new FlowPanel() {
    preferredSize = new Dimension(800,50)
    background = java.awt.Color.DARK_GRAY
    contents ++= List(upBtn, downBtn, leftBtn, rightBtn, undoBtn, redoBtn)
  }

  def getPanel(): BorderPanel = {
    new BorderPanel {
      add(menuBar, BorderPanel.Position.North)
      add(dungeonGrid, BorderPanel.Position.Center)
      add(controlPanel(), BorderPanel.Position.South)
    }
  }
}
