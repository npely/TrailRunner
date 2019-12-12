package controller

import model.Field
import model.maps.Level2
import model.player.PlayerFactory
import org.scalatest.{Matchers, WordSpec}
import util.Observer

class ControllerSpec extends WordSpec with Matchers{

  "A Controller" when {
    "observed by an Observer"  should {
      val player = PlayerFactory.createPlayer2()
      val field = Field(0)
      val level = new Level2
      val controller = new Controller(player, field, level)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after player makes a move" in {
        controller.fieldIsBroken should be(true)
        controller.fieldIsSet should be(true)
        player.yPos = 1
        controller.playerMoveUp()
        controller.playerMoveRight()
        controller.playerMoveDown()
        controller.playerMoveLeft()
        observer.updated should be(true)
        controller.player.yPos should be(1)
      }
      "notify its Observer when player stands on an other field" in {
        controller.playerStandsOnField()
        observer.updated should be(true)
        controller.field.value should be(0)
      }
      "notify its Observer when counter increases" in {
        controller.count
        observer.updated should be(true)
        controller.counter should be(1)
      }
      "should have a game string representation" in {
        controller.playerToGameString should be ("P")
      }
      "should have a string representation" in {
        controller.playerToString should be("Pete")
      }
      "Player1 and Player3" in {
        val player1 = PlayerFactory.createPlayer1()
        player1.id should be(1)
        player1.toString should be("Niklas")
        val player3 = PlayerFactory.createPlayer3()
        player3.id should be(3)
        player3.toString should be("Roland")
        controller.remove(observer)
      }
      "undo and redo should" in {
        controller.undo should be(controller.undo)
        controller.redo should be(controller.redo)
      }
      "test2" in {
        val test2 = new MoveCommands.MoveRightCommand(controller)
        test2.redoStep should be(test2.redoStep)
        test2.undoStep should be(test2.undoStep)
      }
      "test1" in {
        val test = new MoveCommands.MoveUpCommand(controller)
        test.undoStep should be(test.undoStep)
        test.redoStep should be(test.redoStep)
      }
    }
  }

}
