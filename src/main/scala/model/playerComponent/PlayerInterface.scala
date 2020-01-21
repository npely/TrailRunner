package model.playerComponent

import model.playerComponent.playerBaseImpl.Player

trait PlayerInterface {
  def toGameString: String
  def moveRight(): Unit
  def moveLeft(): Unit
  def moveUp(): Unit
  def moveDown(): Unit
  var xPos: Int
  var yPos: Int
}

trait PlayerFactoryInterface {
  def createPlayer1(): PlayerInterface
  def createPlayer2(): PlayerInterface
  def createPlayer3(): PlayerInterface
}
