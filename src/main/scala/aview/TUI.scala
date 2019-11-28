package aview

import java.io.FileNotFoundException
import model.maps.{Level1, Level2, Level3}
import scala.io.{BufferedSource, Source}
import util.Observer
import controller.Controller

/**
 * @author npely @author screp99
 * This class makes the game playable with the console
 * @param controller for communication with model
 */
class TUI(controller: Controller) extends Observer {

  controller.add(this)

  val greetings: String = "Welcome to TrailRunner!"
  val mainMenu: List[String] = List("Begin a new game!", "End game!")
  val endMenu: List[String] = List("Begin a new game!", "End game!")
  val banner: String = getTitleBanner

  val TUIMODE_INVALID_ACTION: Int = -4
  val TUIMODE_LOSE: Int = -3
  val TUIMODE_WIN: Int = -2
  val TUIMODE_QUIT: Int = -1
  val TUIMODE_RUNNING: Int = 0
  val TUIMODE_MAINMENU: Int = 1
  val TUIMODE_SELECTION: Int = 2
  //TODO: val TUIMODE_CHOOSE_NAME: Int = 3

  val INVALID_INPUT: Int = 99

  var tuiMode: Int = TUIMODE_MAINMENU
  var oldtuiMode: Int = tuiMode

  /**
   * Gets the source of the TitleBanner
   * @return TrailRunnerStart.txt
   */
  private def getTitleBanner: String = {
    var bufferedSource: BufferedSource = null
    try {
      bufferedSource = Source.fromFile("src/TrailRunnerStart.txt")
    } catch {
      case _: FileNotFoundException => bufferedSource = Source.fromFile("./src/TrailRunnerStart.txt")
      case _: Throwable =>
    }

    val titleBanner = bufferedSource.mkString
    bufferedSource.close
    titleBanner
  }

  /**
   * Calls the evaluation method for the current tuiMode
   * @param input string read from console
   * @return the fitting evaluation method
   */
  def evaluateInput(input: String): Int = tuiMode match {
    case TUIMODE_RUNNING => evaluateRunning(input)
    case TUIMODE_MAINMENU => evaluateMainMenu(input)
    case TUIMODE_SELECTION => evaluateSelection(input)
    case TUIMODE_WIN => evaluateWin(input)
    case TUIMODE_LOSE => evaluateLose(input)
    case TUIMODE_INVALID_ACTION =>
      tuiMode = oldtuiMode
      evaluateInput(input)
  }

  /**
   * Evaluates the input if user lost the game
   * @param inputStr string read from console
   * @return tuiMode
   */
  def evaluateMainMenu(inputStr: String): Int = {
    oldtuiMode = TUIMODE_MAINMENU
    val input = inputStr
    if (input.equals("1")) {
      tuiMode = TUIMODE_SELECTION
      updateScreen()
    } else if (input .equals("2")) {
      tuiMode = TUIMODE_QUIT
    }
    else {
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
    oldtuiMode = TUIMODE_SELECTION
    var input = inputStr
    if(input.equals("1")) {
      controller.level = Level1
      controller.player = Level1.player
      controller.playerStandsOnField()
      tuiMode = TUIMODE_RUNNING
      updateScreen()
    }
    else if(input.equals("2")) {
      controller.level = Level2
      controller.player = Level2.player
      controller.playerStandsOnField()
      tuiMode = TUIMODE_RUNNING
      updateScreen()
    }
    else if(input.equals("3")) {
      controller.level = Level3
      controller.player = Level3.player
      controller.playerStandsOnField()
      tuiMode = TUIMODE_RUNNING
      updateScreen()
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
      updateScreen()
    }
    tuiMode
  }

  /**
   * Evaluates the input if user is playing the game
   * @param input string read from console
   * @return tuiMode
   */
  def evaluateRunning(input: String): Int = {
    oldtuiMode = TUIMODE_RUNNING
    input match {
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
      case _ =>
        tuiMode = TUIMODE_INVALID_ACTION
        updateScreen()
    }
    tuiMode
  }

  /**
   * Evaluates a move by the player
   */
  def evaluateMove(): Int = {
    if (!controller.levelLose()) {
      tuiMode = TUIMODE_RUNNING
      if (controller.levelWin()) {
        tuiMode = TUIMODE_WIN
        updateScreen()
      }
    }
    else {
      tuiMode = TUIMODE_LOSE
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
    oldtuiMode = TUIMODE_WIN
    evaluateEndOfGame(inputStr)
  }

  /**
   * Evaluates the input if user loses the game
   * @param inputStr string read from console
   * @return tuiMode
   */
    //TODO:
  def evaluateLose(inputStr: String): Int = {
      oldtuiMode = TUIMODE_LOSE
      evaluateEndOfGame(inputStr)
  }

  /**
   * Evaluates when the game is over
   * @return tuiMode
   */
  def evaluateEndOfGame(inputStr: String): Int = {
    val input = inputStr
    if (input.equals("1")) {
      tuiMode = TUIMODE_SELECTION
      updateScreen()
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
   * Builds the string for output
   * @return output
   */
  def buildOutputStringForMenus(): String = {

    if (tuiMode >= 1) {
      output = output + banner
      if (tuiMode == TUIMODE_MAINMENU) {
        buildOutputStringForMainMenu()
      }
      else if (tuiMode == TUIMODE_SELECTION) {
        buildOutputStringForSelectionMenu()
      }
    }
    else if (tuiMode == TUIMODE_RUNNING) {
      buildOutputStringForRunningGame()
    }
    else if (tuiMode == TUIMODE_INVALID_ACTION) {
      buildOutputStringForInvalidAction()
    }
    else if (tuiMode == TUIMODE_WIN) {
      buildOutputStringForWin()
    }
    else if (tuiMode == TUIMODE_LOSE) {
      buildOutputStringForLose()
    }
    output
  }

  /**
   * Builds the tui String for main menu
   * @return output
   */
  def buildOutputStringForMainMenu() : String = {
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
   * Builds the tui String if user presses invalid keys
   * @return output
   */
  def buildOutputStringForInvalidAction() : String = {
    if (!output.contains("Ungültige Eingabe!")) {
      output += "Ungültige Eingabe!\n"
    }
    output
  }

  /**
   * Builds the tui String if user wins the game
   * @return output
   */
  def buildOutputStringForWin(): String = {
    output = "\nCongratulations, you've found your way out of the dungeon!\n"
    var index = 1
    for (x <- endMenu) {
      output += "'" + index.toString + "': " + x + "\n"
      index += 1
    }
    output
  }

  /**
   * Builds the tui String if user loses the game
   * @return output
   */
  def buildOutputStringForLose(): String = {
    output = "\nYou died! Try again?\n"
    var index = 1
    for (x <- endMenu) {
      output += "'" + index.toString + "': " + x + "\n"
      index += 1
    }
    output
  }

  /**
   * Builds the tui String depending on the current tui mode
   * @return output
   */
  override def toString: String = buildOutputStringForMenus()

  /**
   * Prints the output on terminal
   */
  def updateScreen(): Unit = println(toString())

  /**
   * Updates if controller changed data
   */
  override def update(): Unit = updateScreen()
}


