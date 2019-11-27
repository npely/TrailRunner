package aview

import controller.Controller
import model.{Field, Player}
import model.maps.Level1
import org.scalatest.{Matchers, WordSpec}

class TUISpec extends WordSpec with Matchers{
  "Tui" when {
    "new" should {
      val tui = new TUI(controller = new Controller(Player("Pete"), field = Field(0), Level1))
      "return 0 in TUIMODE_SELECTION when a levelnumber is selected" in {
        tui.evaluateSelection(1.toString) should be(0)
      }

      "return -4 in TUIMODE_SELECTION when anything else then a levelnumber is selected" in {
        tui.evaluateSelection((-1).toString) should be(-4)
      }

      "return 2 in TUIMODE_MAINMENU when 1 is selected" in {
        tui.tuiMode = tui.TUIMODE_MAINMENU
        tui.evaluateInput(input = 1.toString) should be(2)
      }

      "return -1 in TUIMODE_MAINMENU when 2 selected" in {
        tui.tuiMode = tui.TUIMODE_MAINMENU
        tui.evaluateInput(input = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_MAINMENU when anything else then 1 or 2 is selected" in {
        tui.tuiMode = tui.TUIMODE_MAINMENU
        tui.evaluateInput(input = (-1).toString) should be(-4)
      }

      "return 0 in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.tuiMode = tui.TUIMODE_RUNNING
        tui.evaluateRunning(input = "d") should be(-3)
      }

      "return -4 in TUIMODE_RUNNING when player makes a invalid move" in {
        tui.tuiMode = tui.TUIMODE_RUNNING
        tui.evaluateRunning(input = "x") should be(-4)
      }

      "return 2 in TUIMODE_WIN when 1 is selected" in {
        tui.tuiMode = tui.TUIMODE_WIN
        tui.evaluateWin(inputStr = 1.toString) should be(2)
      }

      "return -1 in TUIMODE_WIN when 2 is selected" in {
        tui.tuiMode = tui.TUIMODE_WIN
        tui.evaluateWin(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_WIN when anything else then 1 or 2 is selected" in {
        tui.tuiMode = tui.TUIMODE_WIN
        tui.evaluateWin(inputStr = (-1).toString) should be(-4)
      }

      "return 2 in TUIMODE_LOSE when 1 is selected" in {
        tui.tuiMode = tui.TUIMODE_LOSE
        tui.evaluateLose(inputStr = 1.toString) should be(2)
      }

      "return -1 in TUIMODE_LOSE when 2 is selected" in {
        tui.tuiMode = tui.TUIMODE_LOSE
        tui.evaluateLose(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_LOSE when anything else then 1 or 2 is selected" in {
        tui.tuiMode = tui.TUIMODE_LOSE
        tui.evaluateLose(inputStr = (-1).toString) should be(-4)
      }


    }
  }
}
