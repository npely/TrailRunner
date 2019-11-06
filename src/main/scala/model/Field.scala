package model

case class Field(value : Int) {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value > 0
}


