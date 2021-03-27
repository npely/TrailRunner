package model.playerComponent.playerBaseImpl

import model.playerComponent.PlayerInterface

case class Player(xPos: Int, yPos: Int) extends PlayerInterface {

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
}


