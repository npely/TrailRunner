package model.fieldComponent

import model.fieldComponent.fieldBaseImpl.Field
import spray.json.JsObject

trait FieldInterface {
  val value: Int
  val fieldType: String
  val fog: Boolean
  val isPlayerOnField: Boolean
  def isBroken: Boolean
  def isSet: Boolean
  def PlayerWalksOnField(): FieldInterface
  def PlayerLeavesField(): FieldInterface
  def setValue(newValue : Int): FieldInterface
  def earthquake: FieldInterface
  def toJson: JsObject
}
