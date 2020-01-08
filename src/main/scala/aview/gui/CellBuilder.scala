package aview.gui

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File

import controller.controllerComponent.{ControllerInterface, DungeonChanged}
import controller.controllerComponent.controllerBaseImpl.Controller
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.border.LineBorder
import model.fieldComponent.fieldBaseImpl.Field

import scala.swing.{BorderPanel, Dimension, GridPanel, Label}
import scala.util.{Failure, Success, Try}

case class CellBuilder(x: Int, y: Int, controller: Controller) extends GridPanel(1, 1) {
  background = java.awt.Color.BLACK
  visible = true
  //listenTo(controller)
  var myField: Field = controller.level.dungeon(x)(y)
  var myPicture: BufferedImage = _
  val path = "src/main/scala/aview/gui/Images/"

  var label: Label = new Label {
    override val size = new Dimension(55,55)
    background = java.awt.Color.BLACK
  }

  def cell: BorderPanel = new BorderPanel() {
    add(label, BorderPanel.Position.Center)
    background = java.awt.Color.BLACK
    //cell.border = new LineBorder(java.awt.Color.BLACK)
    listenTo(controller)
    setCellPicture

    reactions += {
      case event: DungeonChanged => redrawCell
    }
  }

  contents += cell

  def redrawCell: Unit = {
    contents.clear()
    contents += cell
    repaint
  }

  def setCellPicture: Unit = {
    myField.value match {
      case 0 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_0_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_0.png"))
        }
      }
      case 1 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_1_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_1.png"))
        }
      }
      case 2 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_2_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_2.png"))
        }
      }
      case 3 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_3_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_3.png"))
        }
      }
      case 4 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_4_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_4.png"))
        }
      }
      case 5 => {
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_5_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_5.png"))
        }
      }
      case _ => myPicture = ImageIO.read(new File(path + "Wall.png"))
    }
    Try(label.icon = new ImageIcon(myPicture.getScaledInstance(label.size.width, label.size.height, Image.SCALE_SMOOTH)))
    match {
      case Success(v) =>
      case Failure(e) => label.icon = new ImageIcon(myPicture)
    }
  }
}
