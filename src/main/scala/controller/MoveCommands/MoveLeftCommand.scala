package controller.MoveCommands

import controller.Controller
import util.Command

class MoveLeftCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    controller.player.moveLeft()
    controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.increaseFieldValueByOne
    controller.player.moveRight()
  }

  override def redoStep: Unit = {
    controller.player.moveLeft()
    controller.playerStandsOnField()
  }
}
