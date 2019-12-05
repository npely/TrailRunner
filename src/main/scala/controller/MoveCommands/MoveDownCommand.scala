package controller.MoveCommands

import controller.Controller
import util.Command

class MoveDownCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
      controller.player.moveDown()
      controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.increaseFieldValueByOne
    controller.player.moveUp()
  }

  override def redoStep: Unit = {
    controller.player.moveDown()
    controller.playerStandsOnField()
  }
}
