package main.scala.model.fieldComponent

import model.Model

trait FieldInterface extends Model {
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
}
