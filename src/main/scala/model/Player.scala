package model
import model.maps.{Level, Level1}

case class Player(name: String) {

  override def toString:String = name

  def toGameString: String = "P"

  var xPos: Int = 0
  var yPos: Int = 0
  var endGame: Boolean = true


  def moveRight: Unit = {
    xPos = xPos + 1
    if (xPos > Level1.level.length - 1 || Level1.level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
  def moveLeft: Unit = {
    xPos = xPos - 1
    if (xPos < 0 || Level1.level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }

  def moveUp: Unit = {
    yPos = yPos - 1
    if (yPos < 0 || Level1.level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
  def moveDown: Unit = {
    yPos = yPos + 1
    if (yPos > Level1.level.length - 1 || Level1.level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
}
