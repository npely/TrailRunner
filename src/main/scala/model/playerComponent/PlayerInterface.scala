package model.playerComponent

import model.playerComponent.playerBaseImpl.Player

trait PlayerInterface {
  def toGameString: String
  def moveRight(): Unit
  def moveLeft(): Unit
  def moveUp(): Unit
  def moveDown(): Unit
}

trait PlayerFactoryInterface {
  def createPlayer1(): Player
  def createPlayer2(): Player
  def createPlayer3(): Player
}
