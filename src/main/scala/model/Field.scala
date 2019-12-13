package model

case class Field(var value : Int) {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  var isPlayerOnField: Boolean = false

  def PlayerStandsOnField():Unit = {
    value = value - 1
  }

  def setValue(value : Int): Unit = this.value = value

  override def toString: String = {
    if (isPlayerOnField){
      return " |P| "
    }
    " |" + value.toString + "| "
  }
}


