package controller

import org.scalatest.{Matchers, WordSpec}
import controllerComponent.controllerMockImpl.Controller
import controllerComponent.controllerBaseImpl.Controller
import model.AllLevels
import model.fieldComponent.fieldMockImpl.Field
import model.fileIOComponent.fileIOMockImpl.FileIO
import model.levelComponent.levelBaseImpl.Level2
import model.levelComponent.levelMockImpl.Level
import model.playerComponent.playerMockImpl.PlayerFactory.Player1
import controllerComponent.controllerBaseImpl.MoveCommands

class ControllerSpec extends WordSpec with Matchers{

  "A Controller" when {
    "new" should {
      val controller = new controllerComponent.controllerMockImpl.Controller()
      controller.fileIO = new FileIO
      controller.level = new Level
      controller.field = new Field
      controller.player = Player1("1")

      "player movements and name should be" in {
        controller.playerToString should be("1")
        controller.playerToGameString should be("P")
        controller.playerMoveDown() should be()
        controller.playerMoveLeft() should be()
        controller.playerMoveRight() should be()
        controller.playerMoveUp() should be()
        controller.playerStandsOnField() should be()
      }
      "changing menus should be" in {
        controller.changeToGame() should be()
        controller.changeToMain() should be()
        controller.changeToSelection() should be()
      }
      "everything field related should be" in {
        controller.fieldIsBroken should be(false)
        controller.fieldIsSet should be(false)
        controller.fieldToString should be(controller.field.toString)
      }
      "everything level related should be" in {
        controller.getImplementedLevels should be(List(controller.level))
        controller.levelToString should be(controller.level.toString)
        controller.levelGetName() should be(controller.level.getName)
        controller.showLevel(controller.level) should be (AllLevels.showLevel(controller.level))
      }
      "game techniques should be" in {
        controller.undo should be()
        controller.redo should be()
        controller.count should be(1)
        controller.win() should be()
        controller.lose() should be()
        controller.load should be()
        controller.save should be()
        controller.levelWin() should be(false)
        controller.levelLose() should be(false)
        controller.increaseFieldValueByOne() should be()
        controller.openDoor() should be()
        controller.initializeGame(controller.level, false) should be()
      }
    }
  }

  "A Controller" when {
    "new" should {
      val controller = new controllerComponent.controllerBaseImpl.Controller
      controller.player = Player1("1")
      controller.field = new Field
      controller.level = new Level2

      /*"player makes a move" in {
        controller.fieldIsBroken should be(true)
        controller.fieldIsSet should be(true)
        controller.player.yPos = 1
        controller.player.xPos = 1
        controller.playerMoveUp() should be(controller.playerMoveUp())
        controller.playerMoveRight() should be(controller.playerMoveRight())
        controller.playerMoveDown() should be(controller.playerMoveDown())
        controller.playerMoveLeft() should be(controller.playerMoveLeft())
        controller.player.yPos should be(1)
        controller.player.xPos should be(1)
      }
      "field" in {
        controller.playerStandsOnField()
        controller.field.value should be(1)
      }
      "counter increases" in {
        controller.count
        controller.counter should be(1)
      }
      "should have a game string representation" in {
        controller.playerToGameString should be ("P")
      }
      "should have a string representation" in {
        controller.playerToString should be("Pete")
      }
      "undo and redo should" in {
        controller.undo should be(controller.undo)
        controller.redo should be(controller.redo)
      }*/

      "player makes a move" in {
        controller.fieldIsBroken should be(true)
        controller.fieldIsSet should be(true)
        controller.player.yPos = 1
        controller.playerMoveUp()
        controller.playerMoveRight()
        controller.playerMoveDown()
        controller.playerMoveLeft()
        controller.player.yPos should be(1)
      }
      "player stands on another field" in {
        controller.playerStandsOnField()
        controller.field.value should be(-104)
        controller.fieldToString should be(" |P| ")
      }
      "counter increases" in {
        controller.count
        controller.counter should be(1)
      }
      "should have a game string representation" in {
        controller.playerToGameString should be("P")
      }
      "should have a string representation" in {
        controller.playerToString should be("1")
      }
      "undo and redo should" in {
        controller.undo should be(controller.undo)
        controller.redo should be(controller.redo)
      }
      "Right Move Command" in {
        val test2 = new MoveCommands.MoveRightCommand(controller)
        test2.redoStep should be(test2.redoStep)
        test2.undoStep should be(test2.undoStep)
      }
      "Up Move Command" in {
        val test = new MoveCommands.MoveUpCommand(controller)
        test.undoStep should be(test.undoStep)
        test.redoStep should be(test.redoStep)
      }
      "change menus" in {
        controller.changeToGame() should be(controller.changeToGame())
        controller.changeToMain() should be(controller.changeToMain())
        controller.changeToSelection() should be(controller.changeToSelection())
      }
      "everything level related" in {
        controller.levelGetName() should be(controller.level.getName)
        controller.levelLose() should be(controller.levelLose())
        controller.levelWin() should be(controller.levelWin())
        controller.showLevel(controller.level) should be(controller.showLevel(controller.level))
        controller.levelToString should be(controller.levelToString)
        controller.getImplementedLevels should be(controller.getImplementedLevels)
        controller.openDoor should be(controller.openDoor)
      }
      "save and load" in {
        controller.initializeGame(controller.level, true)
        controller.save should be(controller.save)
        controller.load should be(controller.load)
      }
      "win and lose" in {
        controller.win() should be(controller.win())
        controller.lose() should be(controller.lose())
      }
    }
  }
}
