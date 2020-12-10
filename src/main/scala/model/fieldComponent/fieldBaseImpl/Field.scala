package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface

case class Field @Inject() (@Named("Zero") valu: Int, typ: String) extends FieldInterface {

  var value = valu

  var fieldType = typ

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  var isPlayerOnField: Boolean = false

  def PlayerStandsOnField(): Unit = {
    value -= 1
  }

  def setValue(value : Int): Unit = this.value = value

  def earthquake: Unit = {
    if (value > 0 && value < 9) {
      value = 0
    }
  }

  override def toString: String = {
    if (value == -99){
      return " |X| "
    }
    if (value == -10 || value == -20){
      return " |T| "
    }
    if (isPlayerOnField){
      return " |P| "
    }
    " |" + value.toString + "| "
  }
}
