package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface

case class Field @Inject() (value: Int, fieldType: String, fog: Boolean, isPlayerOnField: Boolean) extends FieldInterface {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  def PlayerWalksOnField(): Field = {
    this.copy(value = this.value - 1, isPlayerOnField = true)
  }

  def PlayerLeavesField(): Field = {
    this.copy(isPlayerOnField = false)
  }

  def setValue(newValue : Int): Field = {
    this.copy(value = newValue)
  }

  def earthquake: Field = {
    if (value > 0 && value < 9) {
      this.copy(value = 0)
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
    if (this.isPlayerOnField){
      return " |P| "
    }
    " |" + value.toString + "| "
  }
}
