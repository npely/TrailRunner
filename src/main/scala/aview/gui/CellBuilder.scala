package aview.gui

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File

import controller.controllerComponent.{ControllerInterface, DungeonChanged}
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import model.fieldComponent.FieldInterface

import scala.swing.{BorderPanel, Dimension, GridPanel, Label}
import scala.util.{Failure, Success, Try}

case class CellBuilder(x: Int, y: Int, controller: ControllerInterface) extends GridPanel(1, 1) {
  background = java.awt.Color.BLACK
  visible = true
  //listenTo(controller)
  var myField: FieldInterface = controller.level.dungeon(x)(y)
  var myPicture: BufferedImage = _
  var actualValue: Int = _
  var actualPlayerStatus: Boolean = _
  val path = "src/main/scala/aview/gui/images/"
  var isDoor: Boolean = x == controller.level.doorX && y == controller.level.doorY

  var label: Label = new Label {
    override val size = new Dimension(55,55)
    background = java.awt.Color.BLACK
  }

  def cell: BorderPanel = new BorderPanel() {
    add(label, BorderPanel.Position.Center)
    background = java.awt.Color.BLACK
    listenTo(controller)
    setCellPicture()


    reactions += {
      case _: DungeonChanged =>
        if (isDoor || myField.value != actualValue || actualPlayerStatus != myField.isPlayerOnField){
          redrawCell()
        }
    }
  }

  contents += cell

  def redrawCell(): Unit = {
    setCellPicture()
    repaint
  }

  def setCellPicture(): Unit = {
    if (myField.value == -1) {
      myPicture = ImageIO.read(new File(path + myField.fieldType + "_0.png"))
    }
    else if (myField.value >= -20 && myField.value <= 20) {
      if (myField.fieldType.equals("Door") || !myField.isPlayerOnField) {
        myPicture = ImageIO.read(new File(path + myField.fieldType + "_" + myField.value + ".png"))
      } else {
        myPicture = ImageIO.read(new File(path + myField.fieldType + "_" + myField.value + "_P.png"))
      }
    }
    else {
      myPicture = ImageIO.read(new File(path + "Wall.png"))
    }
    actualValue = myField.value
    actualPlayerStatus = myField.isPlayerOnField
    Try(label.icon = new ImageIcon(myPicture.getScaledInstance(label.size.width, label.size.height, Image.SCALE_SMOOTH)))
    match {
      case Success(v) =>
      case Failure(e) => label.icon = new ImageIcon(myPicture)
    }
  }
}
