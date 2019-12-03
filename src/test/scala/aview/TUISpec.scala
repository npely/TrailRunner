package aview

import controller.Controller
import model.Field
import model.maps.Level1
import model.player.{Player, PlayerFactory}
import org.scalatest.{Matchers, WordSpec}

class TUISpec extends WordSpec with Matchers{
  "Tui" when {
    "new" should {
      val tui = new TUI(controller = new Controller(PlayerFactory.createPlayer1(), field = Field(0), new Level1))
      "return 0 in TUIMODE_SELECTION when a levelnumber is selected" in {
        tui.evaluateSelection(1.toString) should be(1)
      }

      "return -4 in TUIMODE_SELECTION when anything else then a levelnumber is selected" in {
        tui.evaluateSelection((-1).toString) should be(1)
      }

      "return 2 in TUIMODE_MAINMENU when 1 is selected" in {
        tui.changeState(new MainMenuState(tui))
        tui.evaluateInput(input = 1.toString) should be(1)
      }

      "return -1 in TUIMODE_MAINMENU when 2 selected" in {
        tui.changeState(new MainMenuState(tui))
        tui.evaluateInput(input = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_MAINMENU when anything else then 1 or 2 is selected" in {
        tui.changeState(new MainMenuState(tui))
        tui.evaluateInput(input = (-1).toString) should be(-1)
      }

      "return 0 in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "d") should be(-1)
      }

      "return -4 in TUIMODE_RUNNING when player makes a invalid move" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "x") should be(-1)
      }

      "return 2 in TUIMODE_WIN when 1 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = 1.toString) should be(-1)
      }

      "return -1 in TUIMODE_WIN when 2 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_WIN when anything else then 1 or 2 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = (-1).toString) should be(-1)
      }

      "return 2 in TUIMODE_LOSE when 1 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = 1.toString) should be(-1)
      }

      "return -1 in TUIMODE_LOSE when 2 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_LOSE when anything else then 1 or 2 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = (-1).toString) should be(-1)
      }


    }
  }
}
