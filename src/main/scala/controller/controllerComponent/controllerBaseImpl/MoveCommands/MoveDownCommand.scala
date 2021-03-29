package controller.controllerComponent.controllerBaseImpl.MoveCommands

import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl.Field
import util.Command

class MoveDownCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.player.moveDown()
    controller.playerWalksOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      level.dungeon(level.doorY)(level.doorX) = level.dungeon(level.doorY)(level.doorX).switchIfDoor
    }
  }

  override def undoStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.increaseFieldValueByOne
    controller.player.moveUp()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field = Field(controller.field.value, controller.field.fieldType, controller.field.fog, true)
    if (level.isDoorOpen) {
      level.dungeon(level.doorY)(level.doorX) = level.dungeon(level.doorY)(level.doorX).switchIfDoor
      level.isDoorOpen = false
    }
  }

  override def redoStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.player.moveDown()
    controller.playerWalksOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      level.dungeon(level.doorY)(level.doorX) = level.dungeon(level.doorY)(level.doorX).switchIfDoor
    }
  }
}
