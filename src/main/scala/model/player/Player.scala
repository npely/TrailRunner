package model.player

import model.Item

import scala.collection.mutable.ListBuffer

trait Player {

  val id: Int

  def toGameString: String = "P"

  var xPos: Int = 0
  var yPos: Int = 0
  var inventory: ListBuffer[Item] = null

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
