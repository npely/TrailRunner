package model.levelComponent

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}

trait LevelInterface {

  def sum() : Int
  def lose(): Boolean
  def win(): Boolean
  def standsPlayerInFrontOfOpenDoor(): Boolean
  def playerStandsOnDoor(): Boolean
  def fillNullValues() : Unit
  def getName: String
  def toString: String
  val dungeon: Array[Array[FieldInterface]]
  val player: PlayerInterface
  var rows: Int
  var columns: Int
  var winX: Int
  var winY: Int
  var doorX: Int
  var doorY: Int
  var isDoorOpen: Boolean
}
