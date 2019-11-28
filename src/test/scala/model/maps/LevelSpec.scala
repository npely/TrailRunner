package model.maps

import org.scalatest.{Matchers, WordSpec}

class LevelSpec extends WordSpec with Matchers {

  "Level 1" when {
    "new" should {
      val level1 = new Level1
      "has startvalue 2" in {
        level1.dungeon(1)(0).value == 2 should be(true)
      }
      "not be zero" in {
        level1.dungeon(1)(1).value != 0 should be(true)
      }
      "player should be on startfield" in {
        level1.player.xPos == 0 should be(true)
      }
      "player should be on startfield: y" in {
        level1.player.yPos == 1 should be(true)
      }
    }

    "Level 2" when {
      "new" should {
        val level2 = new Level2
        "has startvalue 1" in {
          level2.dungeon(4)(0).value == 1 should be(true)
        }
        "not be null" in {
          level2.dungeon(2)(1).value != 0 should be(true)
        }
        "player should be on startfield" in {
          level2.player.xPos == 0 should be(true)
        }
        "player should be on startfield: y" in {
          level2.player.yPos == 4 should be(true)
        }
      }
    }

    "Level 3" when {
      "new" should {
        val level3 = new Level3
        "has startvalue 1" in {
          level3.dungeon(1)(0).value == 1 should be(true)
        }
        "not be null" in {
          level3.dungeon(1)(3).value != 0 should be(true)
        }
        "player should be on startfield" in {
          level3.player.xPos == 0 should be(true)
        }
        "player should be on startfield: y" in {
          level3.player.yPos == 1 should be(true)
        }
      }
    }
  }
}
