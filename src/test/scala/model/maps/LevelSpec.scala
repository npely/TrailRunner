package model.maps

import org.scalatest.{Matchers, WordSpec}

class LevelSpec extends WordSpec with Matchers {

  "Level 1" when {
    "new" should {
      var level2 = Level2.level
      "have startvalue 1" in {
        level2(4)(0).value == 1 should be(true)
      }
      "not be null" in {
        level2(2)(1).value != 0 should be(true)
      }
      "player should be on startfield" in {
        Level2.player.xPos == 0 should be(true)
      }
      "player should be on startfield: y" in {
        Level2.player.yPos == 4 should be(true)
      }
    }

  }

}
