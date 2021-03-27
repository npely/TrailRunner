package model.playerComponent.playerBaseImpl

import model.playerComponent.PlayerInterface

case class Player(xPos: Int, yPos: Int) extends PlayerInterface {

  def toGameString: String = "P"

  def moveRight(): Player = {
    Player(this.xPos + 1, this.yPos)
  }

  def moveLeft(): Player = {
    Player(this.xPos - 1, this.yPos)
  }

  def moveUp(): Player = {
    Player(this.xPos, this.yPos -1)
  }

  def moveDown(): Player = {
    Player(this.xPos, this.yPos +1)
  }
}


