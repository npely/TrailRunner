package model

import model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "Field" when {
    val field = new Field(1, "Ground", false, false)

    "isBroken is called" should {
      "return false" in {
        field.isBroken should be(false)
      }
    }
  }
}
