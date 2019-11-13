package model
import model.maps.{Level, Level1}

case class Player(name: String, level: Array[Array[Field]]) {

  override def toString:String = name

  def toGameString: String = "P"

  var xPos: Int = 0
  var yPos: Int = 0
  var endGame: Boolean = true


  def moveRight(): Unit = {
    xPos += 1
    if (xPos > level.length - 1 || level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
  def moveLeft(): Unit = {
    xPos -= 1
    if (xPos < 0 || level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }

  def moveUp(): Unit = {
    yPos -= 1
    if (yPos < 0 || level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
  def moveDown(): Unit = {
    yPos += 1
    if (yPos > level.length - 1 || level(yPos)(xPos).value == 0) {
      System.out.println("You died!")
      endGame = false
    }
  }
}
