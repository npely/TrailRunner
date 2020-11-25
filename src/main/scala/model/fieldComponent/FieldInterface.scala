package model.fieldComponent

import model.fieldComponent.fieldBaseImpl.Field

trait FieldInterface {
  def isBroken: Boolean
  def isSet: Boolean
  var isPlayerOnField: Boolean
  def PlayerStandsOnField(): Unit
  def setValue(value : Int): Unit
  var value: Int
  var fieldType: String
}
