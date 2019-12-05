package controller.MoveCommands

import controller.Controller
import util.Command

class MoveUpCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    controller.player.moveUp()
    controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.increaseFieldValueByOne
    controller.player.moveDown()
  }

  override def redoStep: Unit = {
    controller.player.moveUp()
    controller.playerStandsOnField()
  }
}
