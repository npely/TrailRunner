package model.maps

import org.scalatest.{Matchers, WordSpec}

class LevelSpec extends WordSpec with Matchers {

  "Level 1" when {
    "new" should {
      val level1 = Level1.dungeon
      "has startvalue 2" in {
        level1(1)(0).value == 2 should be(true)
      }
      "not be zero" in {
        level1(1)(1).value != 0 should be(true)
      }
      "player should be on startfield" in {
        Level1.player.xPos == 0 should be(true)
      }
      "player should be on startfield: y" in {
        Level1.player.yPos == 1 should be(true)
      }
    }

    "Level 2" when {
      "new" should {
        val level2 = Level2.dungeon
        "has startvalue 1" in {
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

    "Level 3" when {
      "new" should {
        val level3 = Level3.dungeon
        "has startvalue 1" in {
          level3(1)(0).value == 1 should be(true)
        }
        "not be null" in {
          level3(1)(3).value != 0 should be(true)
        }
        "player should be on startfield" in {
          Level3.player.xPos == 0 should be(true)
        }
        "player should be on startfield: y" in {
          Level3.player.yPos == 1 should be(true)
        }
      }
    }
  }
}
