package controller.MoveCommands

import controller.Controller
import util.Command

class MoveRightCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    controller.field.isPlayerOnField = false
    controller.player.moveRight()
    controller.playerStandsOnField()
  }

  override def undoStep: Unit = {
    controller.field.isPlayerOnField = false
    controller.increaseFieldValueByOne
    controller.player.moveLeft()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field.isPlayerOnField = true
  }

  override def redoStep: Unit = {
    controller.field.isPlayerOnField = false
    controller.player.moveRight()
    controller.playerStandsOnField()
  }
}
