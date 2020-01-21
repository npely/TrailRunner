package controller.controllerComponent.controllerBaseImpl.MoveCommands

import controller.controllerComponent.controllerBaseImpl.Controller
import util.Command

class MoveDownCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
      controller.field.isPlayerOnField = false
      controller.player.moveDown()
      controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.field.isPlayerOnField = false
    controller.increaseFieldValueByOne
    controller.player.moveUp()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field.isPlayerOnField = true
  }

  override def redoStep: Unit = {
    controller.field.isPlayerOnField = false
    controller.player.moveDown()
    controller.playerStandsOnField()
  }
}
