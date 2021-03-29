package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface

case class Field @Inject() (@Named("Zero") value: Int, fieldType: String = "", fog: Boolean = false, isPlayerOnField: Boolean = false) extends FieldInterface {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  override def PlayerWalksOnField(): FieldInterface = {
    this.copy(value = this.value - 1, isPlayerOnField = true)
  }

  override def PlayerLeavesField(): FieldInterface = {
    this.copy(isPlayerOnField = false)
  }

  def setValue(newValue : Int): Field = this.copy(value = newValue)

  def earthquake: Field = {
    if (value > 0 && value < 9) {
      this.copy(value = 0)
    }
    this
  }

  override def switchIfDoor: FieldInterface = {
    if (value > 8 || value < -8) {
      this.copy(value = this.value * -1)
    }
    this
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
