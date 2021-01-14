package model.fieldComponent

import model.fieldComponent.fieldBaseImpl.Field

trait FieldInterface {
  def isBroken: Boolean
  def isSet: Boolean
  var isPlayerOnField: Boolean
  def PlayerStandsOnField(): Unit
  def setValue(value : Int): Unit
  def earthquake: Unit
  var value: Int
  var fieldType: String
  var fog: Boolean
}
