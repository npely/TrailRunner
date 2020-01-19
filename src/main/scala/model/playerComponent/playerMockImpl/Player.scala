package model.playerComponent.playerMockImpl

import model.playerComponent.PlayerInterface

trait Player extends PlayerInterface {

  val id: Int = 1

  def toGameString: String = "P"

  var xPos: Int = 0
  var yPos: Int = 0

  def moveRight(): Unit = {}

  def moveLeft(): Unit = {}

  def moveUp(): Unit = {}

  def moveDown(): Unit = {}
}
