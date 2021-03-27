package model.levelComponent.levelBaseImpl

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

abstract class Level extends LevelInterface {

  val rows: Int = 10
  val columns: Int = 10
  val fieldDoor: FieldInterface = Field(-10, "Door", false, false)
  val fieldDoorReversed: FieldInterface = Field(-20, "Door", false, false)
  val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  def sum() : Int = {
    var sum = 0
    for (i <- 0 until rows; j <- 0 until columns) {
      if (dungeon(i)(j).value > 0 && dungeon(i)(j).value < 10){
        sum += dungeon(i)(j).value
      }
    }
    sum
  }

  def lose(): Boolean = {
    if (dungeon(player.yPos)(player.xPos).value < 0) {
      return true
    }
    false
  }

  def playerStandsOnDoor(): Boolean = {
    if (player.xPos == doorX && player.yPos == doorY) {
      return true
    }
    false
  }

  def win(): Boolean = {
    if (isDoorOpen && playerStandsOnDoor()) {
      return true
    }
    false
  }

  def fillNullValues() : Unit = {
    for (i <- 0 until rows; j <- 0 until columns) {
      if (dungeon(i)(j) == null) {
        dungeon(i)(j) = Field(-99, "Wall", false, false)
      }
    }
  }

  def getName: String = name

  override def toString: String = dungeon.map(_.mkString).mkString("\n")
}


