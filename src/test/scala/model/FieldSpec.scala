import org.scalatest.{Matchers, WordSpec}
import model.Field

class FieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "not set to any value_1" should {
      val brokenField = Field(1, 1, 0)
      "have value 0_1" in {
        brokenField.value should be(0)
      }
      "have row 1_1" in {
        brokenField.row should be(1)
      }
      "have column 1_1" in {
        brokenField.column should be(1)
      }
      "be set_1" in {
        brokenField.isSet should be(true)
      }
      "not be null" in {
        brokenField != null should be(true)
      }
      "be broken_1" in {
        brokenField.isBroken should be(true)
      }
    }

    "not set to any value_2" should {
      val brokenNonExistingField = Field(0, 0, 0)
      "have value 0_2" in {
        brokenNonExistingField.value should be(0)
      }
      "not be set_1" in {
        brokenNonExistingField.isSet should be(false)
      }
      "be broken_2" in {
        brokenNonExistingField.isBroken should be(true)
      }
    }

    "set to a specific value_1" should {
      val nonExistingField = Field(0, 0, 3)
      "return that value_1" in {
        nonExistingField.value should be(3)
      }
      "not be set_2" in {
        nonExistingField.isSet should be(false)
      }
      "not be broken_1" in {
        nonExistingField.isBroken should be(false)
      }
    }

    "set to a specific value_2" should {
      val existingField = Field(2, 3, 2)
      "return that value_2" in {
        existingField.value should be(2)
      }
      "be set_2" in {
        existingField.isSet should be(true)
      }
      "not be broken_2" in {
        existingField.isBroken should be(false)
      }
    }
  }
}
