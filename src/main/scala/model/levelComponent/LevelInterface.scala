package model.levelComponent

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

trait LevelInterface {

  val dungeon: Array[Array[FieldInterface]]
  val name: String
  val player: PlayerInterface
  val rows: Int
  val columns: Int
  val winX: Int
  val winY: Int
  val doorX: Int
  val doorY: Int
  val isDoorOpen: Boolean
  def sum() : Int
  def lose(): Boolean
  def win(): Boolean
  def standsPlayerInFrontOfOpenDoor(): LevelInterface
  def playerStandsOnDoor(): Boolean
  def fillNullValues() : Unit
  def getName: String
  def toString: String
}
