package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface

case class Field @Inject() (@Named("Zero") valu: Int) extends FieldInterface {

  var value = valu

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  var isPlayerOnField: Boolean = false

  def PlayerStandsOnField():Unit = {
    value = value - 1
  }

  def setValue(value : Int): Unit = this.value = value

  override def toString: String = {
    if (value == -99){
      return " |X| "
    }
    if (value == -10){
      return " |T| "
    }
    if (isPlayerOnField){
      return " |P| "
    }
    " |" + value.toString + "| "
  }
}