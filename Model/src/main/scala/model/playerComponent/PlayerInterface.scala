package model.playerComponent

import model.Model

trait PlayerInterface extends Model {
  val xPos: Int
  val yPos: Int
  def toGameString: String
  def moveRight(): PlayerInterface
  def moveLeft(): PlayerInterface
  def moveUp(): PlayerInterface
  def moveDown(): PlayerInterface
}
