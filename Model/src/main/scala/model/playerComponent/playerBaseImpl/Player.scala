package main.scala.model.playerComponent.playerBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import model.playerComponent.PlayerInterface
import spray.json.{JsNumber, JsObject}

case class Player @Inject() (@Named("Zero") xPos: Int, @Named("Zero") yPos: Int) extends PlayerInterface {

  def toGameString: String = "P"

  def moveRight(): Player = {
    this.copy(xPos = this.xPos + 1)
  }

  def moveLeft(): Player = {
    this.copy(xPos = this.xPos - 1)
  }

  def moveUp(): Player = {
    this.copy(yPos = this.yPos - 1)
  }

  def moveDown(): Player = {
    this.copy(yPos = this.yPos + 1)
  }

  override def toJson(): JsObject = JsObject(
    "xPos" -> JsNumber(this.xPos),
    "yPos" -> JsNumber(this.yPos)
  )
}


