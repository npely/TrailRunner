package model.fieldComponent.fieldMockImpl

import model.fieldComponent.FieldInterface

class Field extends FieldInterface{
  override def isBroken: Boolean = true

  override def isSet: Boolean = true

  override var isPlayerOnField: Boolean = true

  override def PlayerStandsOnField(): Unit = {}

  override def setValue(value: Int): Unit = {}

  override var value: Int = 1

  override var fieldType: String = _
}
