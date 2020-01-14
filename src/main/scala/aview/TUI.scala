package aview

import controller.Controller
import de.htwg.se.sudoku.controller._
import model.maps.{Level, Level1, Level2, Level3}

import scala.io.{BufferedSource, Source}
import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

/**
 * @author npely @author screp99
 * This class makes the game playable with the console
 * @param controller for communication with model
 */
class TUI(controller: Controller) extends Reactor {

  listenTo(controller)
  var state: State = new MainMenuState(this)

  val greetings: String = "Welcome to TrailRunner!"
  val mainMenu: List[String] = List("Begin a new game!", "End game!")
  val endMenu: List[String] = List("Begin a new game!", "End game!")
  val banner: String = getTitleBanner

  val TUIMODE_INVALID_ACTION: Int = 1
  val TUIMODE_QUIT: Int = -1
  val TUIMODE_MAINMENU: Int = 1

  val INVALID_INPUT: Int = 99

  var tuiMode: Int = TUIMODE_MAINMENU
  /**
   * Gets the source of the TitleBanner
   * @return TrailRunnerStart.txt
   */
  private def getTitleBanner: String = {
    var banner = ""
    Try(Source.fromFile("src/TrailRunnerStart.txt")) match {
      case Success(v) => banner = v.asInstanceOf[BufferedSource].mkString; v.asInstanceOf[BufferedSource].close()
      case Failure(e) => banner = "<Error while reading from " + "\"" + "src/TrailRunnerStart.txt" + "\">"
    }
    banner
  }

  def changeState(state: State): Unit = {
    this.state = state
  }

  def evaluateInput(input: String): Int = {
    state.evaluateInput(input)
  }

  /**
   * Evaluates the input if user lost the game
   * @param inputStr string read from console
   * @return tuiMode
   */
  def evaluateMainMenu(inputStr: String): Int = {
    val input = inputStr
    if (input.equals("1")) {
      //changeState(new SelectionState(this))
      controller.changeToSelection()
    } else if (input .equals("2")) {
      tuiMode = TUIMODE_QUIT
    }
    else {
      //changeState(new InvalidActionState(this))
      tuiMode = TUIMODE_INVALID_ACTION
      updateScreen()
    }
    tuiMode
  }

  /**
   * Evaluates the input if user selects a level
   * @param inputStr string read from console
   * @return tuiMode
   */
  def evaluateSelection(inputStr: String): Int = {
    val input = inputStr
    if(input.equals("1")) {
      initializeLevel(new Level1)
    }
    else if(input.equals("2")) {
      initializeLevel(new Level2)
    }
    else if(input.equals("3")) {
      initializeLevel(new Level3)
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
      updateScreen()
    }
    tuiMode
  }

  def initializeLevel(level: Level): Unit = {
    controller.level = level
    controller.player = controller.level.player
    controller.playerStandsOnField()
    //changeState(new RunningState(this))
    controller.changeToGame()
  }

  /**
   * Evaluates the input if user is playing the game
   * @param input string read from console
   * @return tuiMode
   */
  def evaluateRunning(input: String): Int = {
    Try(input match {
      case "d" =>
        controller.playerMoveRight()
        evaluateMove()
      case "w" =>
        controller.playerMoveUp()
        evaluateMove()
      case "s" =>
        controller.playerMoveDown()
        evaluateMove()
      case "a" =>
        controller.playerMoveLeft()
        evaluateMove()
      case "z" =>
        controller.undo
        evaluateMove()
      case "y" =>
        controller.redo
        evaluateMove()
      case _ =>
        tuiMode = TUIMODE_INVALID_ACTION
        updateScreen()
    }
    ) match {
      case Success(s) => tuiMode
      case Failure(f) => println("Invalid Mode");
        tuiMode
    }
  }

  /**
   * Evaluates a move by the player
   */
  def evaluateMove(): Int = {
    if (!controller.levelLose()) {
      changeState(new RunningState(this))
      if (controller.levelWin()) {
        changeState(new WinState(this))
        updateScreen()
      }
    }
    else {
      changeState(new LoseState(this))
      updateScreen()
    }
    tuiMode
  }

  /**
   * Evaluates the input if user wins the game
   * @param inputStr string read from console
   * @return tuiMode
   */
  def evaluateWin(inputStr: String): Int = {
    controller.win()
    evaluateEndOfGame(inputStr)
  }

  /**
   * Evaluates the input if user loses the game
   * @param inputStr string read from console
   * @return tuiMode
   */
    //TODO:
  def evaluateLose(inputStr: String): Int = {
      controller.lose()
      evaluateEndOfGame(inputStr)
  }

  /**
   * Evaluates when the game is over
   * @return tuiMode
   */
  def evaluateEndOfGame(inputStr: String): Int = {
    val input = inputStr
    if (input.equals("1")) {
      controller.changeToMain()
    } else if (input.equals("2")) {
      tuiMode = TUIMODE_QUIT
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
      updateScreen()
    }
    tuiMode
  }

  /**
   * This variable
   */
  var output: String = ""

  /**
   * Builds the tui String for main menu
   * @return output
   */
  def buildOutputStringForMainMenu() : String = {
    output = output + banner
    output = output + greetings + "\n"
    var index = 1
    for (x <- mainMenu) {
      output = output + "'" + index.toString + "': " + x + "\n"
      index += 1
    }
    output
  }

  /**
   * Builds the tui String for selection menu
   * @return output
   */
  def buildOutputStringForSelectionMenu() : String = {
    output = "Level Selection" + "\n"
    var index = 1
    for (x <- controller.getImplementedLevels) {
      output = output + "'" + index.toString + "': " + controller.showLevel(x) + "\n"
      index += 1
    }
    output
  }

  /**
   * Builds the tui String for running game
   * @return output
   */
  def buildOutputStringForRunningGame() : String = {
      output = controller.levelToString + "\n" + "Player:" + "[ x: " + (controller.player.xPos + 1) + " | y: " + (controller.player.yPos + 1) + " ]" +
        "\n" + "Ziel: [ x: " + (controller.level.winX + 1) + " | y: " + (controller.level.winY + 1) + "]" + "\n"
      output
  }

  /**
   * Builds the tui String if user wins the game
   * @return output
   */
  def buildOutputStringForWin(): String = {
    output = "\nCongratulations, you've found your way out of the dungeon!\n"
    buildOutputStringForEndGame()
  }

  /**
   * Builds the tui String if user loses the game
   * @return output
   */
  def buildOutputStringForLose(): String = {
    output = "\nYou died! Try again?\n"
    buildOutputStringForEndGame()
  }

  def buildOutputStringForEndGame(): String = {
    var index = 1
    for (x <- endMenu) {
      output += "'" + index.toString + "': " + x + "\n"
      index += 1
    }
    output
  }

  /**
   * Builds the tui String depending on the current tui mode
   *
   * @return output
   */
  override def toString: String = state.toString

  /**
   * Prints the output on terminal
   */
  def updateScreen(): Unit = print(toString())

  reactions += {
    case event: DungeonChanged => updateScreen()
    case event: ChangeToGame =>
      changeState(new RunningState(this))
      updateScreen()
    case event: ChangeToSelection =>
      changeState(new SelectionState(this))
      updateScreen()
    case event: ChangeToMain =>
      changeState(new MainMenuState(this))
      updateScreen()
    case event: Lose =>
      changeState(new LoseState(this))
      updateScreen()
    case event: Win =>
      changeState(new WinState(this))
      updateScreen()
  }
}


