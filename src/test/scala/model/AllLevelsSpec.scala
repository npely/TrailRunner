package model

import model.levelComponent.levelBaseImpl.Level1
import org.scalatest.{Matchers, WordSpec}

class AllLevelsSpec extends WordSpec with Matchers{
  "AllLevels" when {
    "Filled with any levels" should {
      "not be empty" in {
        AllLevels.getImplementedList().isEmpty  should be(false)
      }
    }

    "All levels" should {
      "have a string representation" in {
        AllLevels.showLevel(new Level1) should be("Level1")
      }
    }
  }

}
