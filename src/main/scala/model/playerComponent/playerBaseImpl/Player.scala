package model.playerComponent.playerBaseImpl

import model.playerComponent.PlayerInterface

trait Player extends PlayerInterface {

  val id: Int

  def toGameString: String = "P"

  var xPos: Int = 0
  var yPos: Int = 0

  def moveRight(): Unit = {
    xPos += 1
  }

  def moveLeft(): Unit = {
    xPos -= 1
  }

  def moveUp(): Unit = {
    yPos -= 1
  }

  def moveDown(): Unit = {
    yPos += 1
  }
}


