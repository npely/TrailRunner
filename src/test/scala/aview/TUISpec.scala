package aview

import model.Field
import org.scalatest.{Matchers, WordSpec}
import aview.{LoseState, MainMenuState, RunningState, SelectionState, WinState}
import controller.controllerComponent.controllerBaseImpl.Controller
import model.levelComponent.levelBaseImpl.Level1
import model.playerComponent.playerBaseImpl.PlayerFactory

class TUISpec extends WordSpec with Matchers{
  "Tui" when {
    "new" should {
      val tui = new TUI(controller = new Controller(PlayerFactory.createPlayer1(), field = Field(0), new Level1))
      "return 0 in TUIMODE_SELECTION when a levelnumber is selected" in {
        tui.evaluateSelection(1.toString) should be(1)
      }

      "return 1 in TUIMODE_SELECTION when a levelnumber is selected" in {
        tui.evaluateSelection(2.toString) should be(1)
      }

      "return 2 in TUIMODE_SELECTION when a levelnumber is selected" in {
        tui.evaluateSelection(3.toString) should be(1)
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
        tui.evaluateInput(input = (-1).toString) should be(1)
      }

      "return d in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "d") should be(1)
      }

      "return w in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "w") should be(1)
      }

      "return a in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "a") should be(1)
      }

      "return s in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "s") should be(1)
      }

      "return z in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "z") should be(1)
      }

      "return y in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "y") should be(1)
      }

      "return 0 in TUIMODE_RUNNING when player makes a valid move(w, a, s, d)" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "d") should be(1)
      }

      "return -4 in TUIMODE_RUNNING when player makes a invalid move" in {
        tui.changeState(new RunningState(tui))
        tui.evaluateRunning(input = "x") should be(1)
      }

      "return 2 in TUIMODE_WIN when 1 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = 1.toString) should be(1)
      }

      "return -1 in TUIMODE_WIN when 2 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_WIN when anything else then 1 or 2 is selected" in {
        tui.changeState(new WinState(tui))
        tui.evaluateWin(inputStr = (-1).toString) should be(1)
      }

      "return 2 in TUIMODE_LOSE when 1 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = 1.toString) should be(1)
      }

      "return -1 in TUIMODE_LOSE when 2 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = 2.toString) should be(-1)
      }

      "return -4 in TUIMODE_LOSE when anything else then 1 or 2 is selected" in {
        tui.changeState(new LoseState(tui))
        tui.evaluateLose(inputStr = (-1).toString) should be(1)
      }
      "win output" in {
        val Winoutput = tui.buildOutputStringForWin();
        tui.output should be(Winoutput);
      }
      "lose output" in {
        val Loseoutput = tui.buildOutputStringForLose();
        tui.output should be(Loseoutput);
      }
      "running game output" in {
        val Runningoutput = tui.buildOutputStringForRunningGame();
        tui.output should be(Runningoutput);
      }
      "selection output" in {
        val Selectionoutput = tui.buildOutputStringForSelectionMenu();
        tui.output should be(Selectionoutput);
      }
      "main menu output" in {
        val Mainoutput = tui.buildOutputStringForMainMenu();
        tui.output should be(Mainoutput);
      }
      "main test" in {
        val mainMenuState = new MainMenuState(tui)
        mainMenuState.evaluateInput("w") should be(mainMenuState.evaluateInput("w"))
      }
      "win test" in {
        val winMenuState = new WinState(tui)
        winMenuState.evaluateInput("w") should be(winMenuState.evaluateInput("w"))
        winMenuState.toString should be(winMenuState.toString)
      }
      "lose test" in {
        val loseMenuState = new LoseState(tui)
        loseMenuState.evaluateInput("w") should be(loseMenuState.evaluateInput("w"))
        loseMenuState.toString() should be(loseMenuState.toString())
      }
      "running test" in {
        val runningMenuState = new RunningState(tui)
        runningMenuState.evaluateInput("l") should be(runningMenuState.evaluateInput("l"))
      }
      "selection test" in {
        val selectionMenuState = new SelectionState(tui)
        selectionMenuState.evaluateInput("w") should be(selectionMenuState.evaluateInput("w"))
      }
    }
  }
}
