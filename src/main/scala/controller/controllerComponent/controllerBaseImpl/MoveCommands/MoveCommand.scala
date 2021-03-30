package controller.controllerComponent.controllerBaseImpl.MoveCommands

import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import util.Command

class MoveCommand(controller: Controller, forwardStep: () => PlayerInterface, backwardStep: () => PlayerInterface) extends Command {

  private def stepForward: Unit = {
    controller.field = controller.field.PlayerLeavesField()
    forwardStep()
    controller.playerStandsOnField()
    if (controller.standsPlayerInFrontOfOpenDoor) {
      switchDoor
    }
  }

  override def doStep: Unit = {
    stepForward
  }

  override def undoStep: Unit = {
    val level = controller.level
    controller.field = controller.field.PlayerLeavesField()
    controller.increaseFieldValueByOne
    backwardStep()
    controller.field = controller.level.dungeon(controller.player.yPos)(controller.player.xPos)
    controller.field = Field(controller.field.value, controller.field.fieldType, controller.field.fog, true)
    if (level.isDoorOpen) {
      switchDoor
      controller.level = level.closeDoor()
    }
  }

  override def redoStep: Unit = {
    stepForward
  }

  def switchDoor: Unit = {
    val level = controller.level
    val old = level.dungeon(level.doorY)(level.doorX)
    level.dungeon(level.doorY)(level.doorX) = old.setValue(old.value * -1)
  }
}
