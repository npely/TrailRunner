package controller

import model.maps.Level
import util.Command

class SolveCommand(controller: Controller) extends Command{
  var memento: Level = controller.level

  override def doStep: Unit = {
    memento = controller.level
    //TODO implement Solver and set it here
  }

  override def undoStep: Unit = {
    val new_memento = controller.level
    controller.level = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.level
    controller.level = memento
    memento = new_memento
  }

}
