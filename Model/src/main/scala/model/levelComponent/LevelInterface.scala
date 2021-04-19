package model.levelComponent

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player
import spray.json.{JsArray, JsObject}

import scala.collection.immutable.Vector1

trait LevelInterface {

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
  def toJson: JsObject
}
