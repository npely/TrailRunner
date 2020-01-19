package model.levelComponent

import model.levelComponent.levelBaseImpl.{Level1, Level2, Level3}
import org.scalatest.{Matchers, WordSpec}

class LevelSpec extends WordSpec with Matchers {

  "Level 1" when {
    "new" should {
      val level1 = new Level1
      "has startvalue 2" in {
        level1.dungeon(1)(0).value should be(-99)
      }
      "not be zero" in {
        level1.dungeon(1)(1).value != 0 should be(true)
      }
      "player should be on startfield" in {
        level1.player.xPos should be(4)
      }
      "player should be on startfield: y" in {
        level1.player.yPos should be(5)
      }
      "sum should be" in {
        level1.sum() should be(5)
      }
    }
  }

  "Level 2" when {
    "new" should {
      val level2 = new Level2
      "has startvalue 1" in {
        level2.dungeon(4)(0).value should be(-99)
      }
      "not be null" in {
        level2.dungeon(2)(1).value != 0 should be(true)
      }
      "player should be on startfield" in {
        level2.player.xPos should be(1)
      }
      "player should be on startfield: y" in {
        level2.player.yPos should be(5)
      }
    }
  }

  "Level 3" when {
    "new" should {
      val level3 = new Level3
      "has startvalue 1" in {
        level3.dungeon(1)(0).value should be(-99)
      }
      "not be null" in {
        level3.dungeon(1)(3).value != 0 should be(true)
      }
      "player should be on startfield" in {
        level3.player.xPos should be(1)
      }
      "player should be on startfield: y" in {
        level3.player.yPos should be(2)
      }
    }
  }
  "MockLevel" when {
    "new" should {
      val level = new model.levelComponent.levelMockImpl.Level
      "sum" in {
        level.sum() should be(1)
      }
      "lose" in {
        level.lose() should be(false)
      }
      "win" in {
        level.win() should be(false)
      }
      "fillNullValues" in {
        level.fillNullValues() should be()
      }
    }
  }
}
