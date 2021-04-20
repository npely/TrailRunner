package model.fieldComponent.fieldBaseImpl
import com.google.inject.Inject
import com.google.inject.name.Named
import model.fieldComponent.FieldInterface
import spray.json.{JsBoolean, JsNumber, JsObject, JsString}

case class Field @Inject() (@Named("Zero") value: Int, @Named("Ground") fieldType: String, @Named("False") fog: Boolean, @Named("False") isPlayerOnField: Boolean) extends FieldInterface {

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
      return this.copy(value = 0)
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

  override def toJson: JsObject = JsObject(
    "value" -> JsNumber(this.value),
    "fieldType" -> JsString(this.fieldType),
    "fog" -> JsBoolean(this.fog),
    "isPlayerOnField" -> JsBoolean(this.isPlayerOnField)
  )
}
