package model.levelComponent.levelBaseImpl

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

abstract class Level extends LevelInterface {

  var name: String
  var playerName: String
  var rows: Int = 10
  var columns: Int = 10
  var startX: Int
  var startY: Int
  var winX: Int
  var winY: Int
  var doorX: Int
  var doorY: Int
  var isDoorOpen: Boolean = false
  val fieldDoor: FieldInterface = Field(-10)
  val fieldDoorReversed: FieldInterface = Field(-20)

  val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)
  val player: PlayerInterface = PlayerFactory.createPlayer1()

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

  def standsPlayerInFrontOfOpenDoor(): Boolean = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      isDoorOpen = true
      return true
    }
    false
  }

  def fillNullValues() : Unit = {
    for (i <- 0 until rows; j <- 0 until columns) {
      if (dungeon(i)(j) == null) {
        dungeon(i)(j) = Field(-99)
      }
    }
  }

  def getName: String = name

  override def toString: String = dungeon.map(_.mkString).mkString("\n")
}


