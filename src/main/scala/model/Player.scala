package model

case class Player(name: String) {

  override def toString:String = name

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
