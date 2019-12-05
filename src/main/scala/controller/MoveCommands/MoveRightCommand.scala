package controller.MoveCommands

import controller.Controller
import util.Command

class MoveRightCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    controller.player.moveRight()
    controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.increaseFieldValueByOne
    controller.player.moveLeft()
  }

  override def redoStep: Unit = {
    controller.player.moveRight()
    controller.playerStandsOnField()
  }
}
