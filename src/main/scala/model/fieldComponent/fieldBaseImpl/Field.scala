package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface

case class Field @Inject() (@Named("Zero") value: Int, fieldType: String, fog: Boolean, isPlayerOnField: Boolean) extends FieldInterface {

  def isBroken:Boolean = value == 0

  def isSet:Boolean = value >= 0

  def PlayerWalksOnField(): Field = {
    Field(this.value - 1, this.fieldType, this.fog, true)
  }

  def PlayerLeavesField(): Field = {
    Field(this.value, this.fieldType, this.fog, false)
  }

  def setValue(value : Int): Field = {
    Field(value, this.fieldType, this.fog, this.isPlayerOnField)
  }

  def earthquake: Field = {
    if (value > 0 && value < 9) {
      Field(0, this.fieldType, this.fog, this.isPlayerOnField)
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
