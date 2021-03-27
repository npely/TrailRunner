package controller.controllerComponent.controllerBaseImpl.MoveCommands

import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl.Field
import util.Command

class MoveLeftCommand(controller: Controller) extends Command {

  override def doStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.player.moveLeft()
    controller.playerStandsOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      switchDoor
    }
  }

  override def undoStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.increaseFieldValueByOne
    controller.player.moveRight()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field = Field(controller.field.value, controller.field.fieldType, controller.field.fog, true)
    if (level.isDoorOpen) {
      switchDoor
      controller.level = level.closeDoor()
    }
  }

  override def redoStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.player.moveLeft()
    controller.playerStandsOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      switchDoor
    }
  }

  def switchDoor: Unit = {
    val level = controller.level
    val old = level.dungeon(level.doorY)(level.doorX)
    level.dungeon(level.doorY)(level.doorX) = old.setValue(old.value * -1)
  }
}
