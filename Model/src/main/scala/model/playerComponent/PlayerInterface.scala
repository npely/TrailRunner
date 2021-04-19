package model.playerComponent

import model.playerComponent.playerBaseImpl.Player
import spray.json.JsObject

trait PlayerInterface {
  val xPos: Int
  val yPos: Int
  def toGameString: String
  def moveRight(): PlayerInterface
  def moveLeft(): PlayerInterface
  def moveUp(): PlayerInterface
  def moveDown(): PlayerInterface
}
