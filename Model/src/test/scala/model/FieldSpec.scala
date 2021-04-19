package model

import model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "Field" when {
    val fieldValue = 1
    val wallValue = -99
    val doorValue = -10
    val fieldType = "Ground"
    val fog = false
    val isPlayerOnField = false
    val normalField = Field(fieldValue, fieldType, fog, isPlayerOnField)
    val wallField = Field(wallValue, fieldType, fog, isPlayerOnField)
    val doorField = Field(doorValue, fieldType, fog, isPlayerOnField)

    "isBroken is called" should {
      "return false" in {
        normalField.isBroken should be(false)
      }
    }

    "isSet is called" should {
      "return true" in {
        normalField.isSet should be(true)
      }
    }

    "PlayerWalksOnField and afterwards PlayerLeavesField is called" should {
      "return a new field" in {
        val walkedOnField = normalField.copy(value = fieldValue - 1, isPlayerOnField = true)
        val leftField = walkedOnField.copy(isPlayerOnField = false)
        normalField.PlayerWalksOnField() should be(walkedOnField)
        walkedOnField.PlayerLeavesField() should be(leftField)
      }
    }

    "setValue is called" should {
      "return a new field" in {
        val newValue = 3;
        val newField = normalField.copy(newValue)
        normalField.setValue(newValue) should be(newField)
      }
    }

    "earthquake is called" should {
      "return a new field" in {
        val brokenField = normalField.copy(value = 0)
        normalField.earthquake should be(brokenField)
      }
      "return the old field" in {
        val brokenField = normalField.copy(value = 0)
        brokenField.earthquake should be(brokenField)
      }
    }

    "toString is called" should {
      "display the different strings" in {
        normalField.toString should be(" |1| ")
        wallField.toString should be(" |X| ")
        doorField.toString should be(" |T| ")
      }
    }
  }
}
