package model.levelComponent

import model.Model
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

trait LevelInterface extends Model{

  val dungeon: Array[Array[FieldInterface]]
  val name: String
  val player: PlayerInterface
  val size: Int
  val winX: Int
  val winY: Int
  val doorX: Int
  val doorY: Int
  val isDoorOpen: Boolean
  def lose(): Boolean
  def win(): Boolean
  def standsPlayerInFrontOfOpenDoor(): LevelInterface
  def closeDoor(): LevelInterface
  def playerStandsOnDoor(): Boolean
  def fillNullValues() : Unit
  def toString: String
}
