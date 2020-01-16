package aview.gui

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File

import controller.controllerComponent.{ControllerInterface, DungeonChanged, OpenDoor}
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
  var doorIsClosed: Boolean = true

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
        if (myField.value != actualValue || actualPlayerStatus != myField.isPlayerOnField){
          if (controller.levelWin()){
            controller.openDoor
          }
          redrawCell()
        }
      case _: OpenDoor =>
        if (myField.value == -10 || myField.value == -20) {
          openDoor()
        }
    }
  }

  contents += cell

  def redrawCell(): Unit = {
    setCellPicture()
    repaint
  }

  def openDoor(): Unit = {
    doorIsClosed = false
    redrawCell()
  }

  def setCellPicture(): Unit = {
    myField.value match {
      case -99 => myPicture = ImageIO.read(new File(path + "Wall.png"))
      case 0 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_0_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_0.png"))
        }
      case 1 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_1_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_1.png"))
        }
      case 2 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_2_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_2.png"))
        }
      case 3 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_3_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_3.png"))
        }
      case 4 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_4_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_4.png"))
        }
      case 5 =>
        if(myField.isPlayerOnField){
          myPicture = ImageIO.read(new File(path + "Ground_5_P.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Ground_5.png"))
        }
      case -10 =>
        if(doorIsClosed){
          myPicture = ImageIO.read(new File(path + "Door1.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Door4.png"))
        }
      case -20 =>
        if(doorIsClosed){
          myPicture = ImageIO.read(new File(path + "Door21.png"))
        }
        else {
          myPicture = ImageIO.read(new File(path + "Door24.png"))
        }
      case _ => myPicture = ImageIO.read(new File(path + "Wall.png"))
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