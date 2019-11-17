package model

case class Field(var value : Int) {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  def PlayerStandsOnField():Unit = {
    value = value - 1
  }

  override def toString: String = " |" + value.toString + "| "
}


