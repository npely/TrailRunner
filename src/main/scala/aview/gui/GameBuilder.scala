package aview.gui

import java.awt.Dimension

import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.event.{ButtonClicked, Key}
import scala.swing.{Action, BorderPanel, Button, Component, Dimension, FlowPanel, GridPanel, Menu, MenuBar, MenuItem}

class GameBuilder(controller: ControllerInterface, gui: GUI) {
  var selectedListIndex = 3

  def menuBar = new MenuBar {
    background = java.awt.Color.DARK_GRAY

    contents += new Menu("File") {
      contents += new MenuItem(Action("Main menu") {
        controller.changeToMain()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
      contents += new MenuItem(Action("Save") {
        controller.save
      })
    }

    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo") {
        controller.undo
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
      })
    }
  }

  var cells: Array[Array[CellBuilder]] = Array.ofDim[CellBuilder](controller.level.rows, controller.level.columns)


  def dungeonGrid: GridPanel = new GridPanel(controller.level.rows, controller.level.columns) {
    override val size = new Dimension(550, 550)
    background = java.awt.Color.BLACK
    for (i <- 0 until controller.level.rows; j <- 0 until controller.level.columns) {
      contents += CellBuilder(i, j, controller)
    }
  }

  var upBtn = new Button("Up") {
    mnemonic = Key.W
    reactions += {
      case e: ButtonClicked => {
        while ("Ice".equals(controller.level.dungeon(controller.player.yPos - 1)(controller.player.xPos).fieldType)) {
          controller.playerMoveUp()
        }
        controller.playerMoveUp()
      }
    }
  }

  var downBtn = new Button("Down") {
    mnemonic = Key.S
    reactions += {
      case e: ButtonClicked => {
        while ("Ice".equals(controller.level.dungeon(controller.player.yPos + 1)(controller.player.xPos).fieldType)) {
          controller.playerMoveDown()
        }
        controller.playerMoveDown()
      }
    }
  }

  var leftBtn = new Button("Left") {
    mnemonic = Key.A
    reactions += {
      case e: ButtonClicked => {
        while ("Ice".equals(controller.level.dungeon(controller.player.yPos)(controller.player.xPos - 1).fieldType)) {
          controller.playerMoveLeft()
        }
        controller.playerMoveLeft()
      }
    }
  }

  var rightBtn = new Button("Right") {
    mnemonic = Key.D
    reactions += {
      case e: ButtonClicked => {
        while ("Ice".equals(controller.level.dungeon(controller.player.yPos)(controller.player.xPos + 1).fieldType)) {
          controller.playerMoveRight()
        }
        controller.playerMoveRight()
      }
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
    preferredSize = new Dimension(800, 50)
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
