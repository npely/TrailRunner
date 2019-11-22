package controller

import model.maps.Level1
import model.{Field, Player}
import org.scalatest.{Matchers, WordSpec}
import util.Observer

class ControllerSpec extends WordSpec with Matchers{

  "A Controller" when {
    "observed by an Observer"  should {
      val player = Player("Pete")
      val field = Field(0)
      val level = Level1
      val controller = new Controller(player, field, level)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after player makes a move" in {
        player.yPos = 1
        controller.playerMoveUp()
        observer.updated should be(true)
        controller.player.yPos should be(0)
      }
      "notify its Observer when player stands on an other field" in {
        controller.playerStandsOnField()
        observer.updated should be(true)
        controller.field.value should be(-1)
      }
    }
  }

}
