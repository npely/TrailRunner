package model

case class Field(value : Int, up : Field, down : Field, left : Field, right : Field) {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value > 0
}


