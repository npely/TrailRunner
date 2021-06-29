package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level @Inject() (@Named("Level") name: String, player: PlayerInterface, @Named("Zero") winX: Int, @Named("Zero") winY: Int, @Named("Zero") doorX: Int, @Named("Zero") doorY: Int, @Named("False") isDoorOpen: Boolean, @Named("Ten") size: Int) extends LevelInterface {

  val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](size, size)

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
    for (i <- 0 until 10; j <- 0 until 10) {
      val dungeonField = Option(dungeon(i)(j))
      dungeonField.getOrElse(Field(-99, "Wall", false, false))
    }
  }

  def standsPlayerInFrontOfOpenDoor(): Level = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      this.copy(isDoorOpen = true)
    }
    this
  }

  def closeDoor() : Level = {
    this.copy(isDoorOpen = false)
  }

  override def toString: String = dungeon.map(_.mkString).mkString("\n")

  def sum() : Int = {
    var sum = 0
    for (i <- 0 until size; j <- 0 until size) {
      if (dungeon(i)(j).value > 0 && dungeon(i)(j).value < 10){
        sum += dungeon(i)(j).value
      }
    }
    sum
  }
}


