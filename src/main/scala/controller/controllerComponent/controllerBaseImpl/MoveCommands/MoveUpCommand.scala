package controller.controllerComponent.controllerBaseImpl.MoveCommands

import controller.controllerComponent.controllerBaseImpl.Controller
import util.Command

class MoveUpCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    val level = controller.level
    controller.field.isPlayerOnField = false
    controller.player.moveUp()
    controller.playerStandsOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      level.dungeon(level.doorY)(level.doorX).value *= -1
    }
  }

  override def undoStep: Unit = {
    val level = controller.level
    controller.field.isPlayerOnField = false
    controller.increaseFieldValueByOne()
    controller.player.moveDown()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field.isPlayerOnField = true
    if (level.isDoorOpen) {
      level.dungeon(level.doorY)(level.doorX).value *= -1
      level.isDoorOpen = false
    }
  }

  override def redoStep: Unit = {
    val level = controller.level
    controller.field.isPlayerOnField = false
    controller.player.moveUp()
    controller.playerStandsOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      level.dungeon(level.doorY)(level.doorX).value *= -1
    }
  }
}
