package model.maps

import org.scalatest.{Matchers, WordSpec}

class LevelSpec extends WordSpec with Matchers {

  "Level 1" when {
    "new" should {
      var level1 = Level1.level
      "have startvalue 1" in {
        level1(4)(0).value == 1 should be(true)
      }
      "not be null" in {
        level1(2)(1).value != null should be(true)
      }
      "player should be on startfield" in {
        Level1.player.xPos == 0 should be(true)
      }
      "player should be on startfield: y" in {
        Level1.player.yPos == 4 should be(true)
      }
    }

  }

}
