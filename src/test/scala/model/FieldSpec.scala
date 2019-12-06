import org.scalatest.{Matchers, WordSpec}
import model.Field

class FieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "set to any value" should {
      var field = new Field(1)
      "have value 1" in {
        field.value should be(1)
      }
      "be set" in {
        field.isSet should be(true)
      }
      "not be null" in {
        field != null should be(true)
      }
      "not be broken" in {
        field.isBroken should be(false)
      }
      "value 1 smaller than before" in {
        field.PlayerStandsOnField
        field.value should be(0)
      }
      "should have a nice String representation" in {
        field.toString should be(" |0| ")
      }
    }

    "not set to any value" should {
      val brokenField = Field(0)
      "have value 0" in {
        brokenField.value should be(0)
      }
      "be set" in {
        brokenField.isSet should be(true)
      }
      "be broken" in {
        brokenField.isBroken should be(true)
      }
    }

    "not existing" should {
      val nonExistingField = Field(-1)
      "have value -1" in {
        nonExistingField.value should be(-1)
      }
      "not be set" in {
        nonExistingField.isSet should be(false)
      }
      "not be broken" in {
        nonExistingField.isBroken should be(false)
      }
    }
  }
}
