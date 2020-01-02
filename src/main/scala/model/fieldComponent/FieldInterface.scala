package model.fieldComponent

trait FieldInterface {
  def isBroken: Boolean
  def isSet: Boolean
  var isPlayerOnField: Boolean
  def PlayerStandsOnField(): Unit
  def setValue(value : Int): Unit
}
