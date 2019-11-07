package model

import org.scalatest.{Matchers, WordSpec}

class DungeonSpec extends  WordSpec with Matchers {
  "A Dungeon" when {
    "new" should {
      val dungeon = Dungeon(1)
      "have the value 1" in {
        dungeon.startField.value == 1 should be(true)
      }
    }
  }
}
