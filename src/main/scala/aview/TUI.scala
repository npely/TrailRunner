package aview

import java.io.FileNotFoundException
import model.maps.{Level1, Level2, Level3}
import scala.io.{BufferedSource, Source}
import util.Observer
import controller.Controller

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  val greetings: String = "Welcome to TrailRunner!"
  val mainMenu: List[String] = List("Begin a new game!", "End game!")
  val winMenu: List[String] = List("End game!")
  val banner: String = getTitleBanner
  //val chosenLevel: List[]

  val TUIMODE_INVALID_ACTION: Int = -4
  val TUIMODE_LOSE: Int = -3
  val TUIMODE_WIN: Int = -2
  val TUIMODE_QUIT: Int = -1
  val TUIMODE_RUNNING: Int = 0
  val TUIMODE_MAINMENU: Int = 1
  val TUIMODE_SELECTION: Int = 2

  val INVALID_INPUT: Int = 99

  var tuiMode: Int = TUIMODE_MAINMENU
  var oldtuiMode: Int = tuiMode

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

  def evaluateInput(input: String): Int = {
    tuiMode match {
      case TUIMODE_RUNNING => evaluateRunning(input)
      case TUIMODE_MAINMENU => evaluateMainMenu(input)
      case TUIMODE_SELECTION => evaluateSelection(input)
      case TUIMODE_WIN => evaluateWin(input)
      case TUIMODE_LOSE => evaluateLoose(input)
      case TUIMODE_INVALID_ACTION =>
        tuiMode = oldtuiMode
        evaluateInput(input)
    }
  }

  def evaluateMainMenu(inputStr: String): Int = {
    oldtuiMode = TUIMODE_MAINMENU
    var inputM: Int = 0

    try {
      inputM = inputStr.toInt
    } catch {
      case _: NumberFormatException => return INVALID_INPUT
    }
    if (inputM == 1) {
      tuiMode = TUIMODE_SELECTION
    } else if (inputM == 2) {
      tuiMode = TUIMODE_QUIT
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
    }
    tuiMode
  }


  def evaluateSelection(inputStr: String): Int = {
    oldtuiMode = TUIMODE_SELECTION
    var inputS = 0

    try {
      inputS = inputStr.toInt
    }
    catch {
      case _: NumberFormatException => return INVALID_INPUT
    }
    if(inputS == 1) {
      controller.level = Level1
      controller.player = Level1.player
      tuiMode = TUIMODE_RUNNING
    }
    else if(inputS == 2) {
      controller.level = Level2
      controller.player = Level2.player
      tuiMode = TUIMODE_RUNNING
    }
    else if(inputS == 3) {
      controller.level = Level3
      controller.player = Level3.player
      tuiMode = TUIMODE_RUNNING
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
    }
    tuiMode
  }

  def evaluateRunning(input: String): Int = {
    oldtuiMode = TUIMODE_RUNNING
    input match {
      case "d" =>
        if (!controller.levelLose()) {
          controller.playerMoveRight()
          tuiMode = TUIMODE_RUNNING
          if (controller.levelWin()) {
            tuiMode = TUIMODE_WIN
            return tuiMode
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
        }
      case "w" =>
        if (!controller.levelLose()) {
          controller.playerMoveUp()
          tuiMode = TUIMODE_RUNNING
          if (controller.levelWin()) {
            tuiMode = TUIMODE_WIN
            return tuiMode
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
        }
      case "s" =>
        if (!controller.levelLose()) {
          controller.playerMoveDown()
          tuiMode = TUIMODE_RUNNING
          if (controller.levelWin()) {
            tuiMode = TUIMODE_WIN
            return tuiMode
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
        }
      case "a" =>
        if (!controller.levelLose()) {
          controller.playerMoveLeft()
          tuiMode = TUIMODE_RUNNING
          if (controller.levelWin()) {
            tuiMode = TUIMODE_WIN
            return tuiMode
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
        }
      case _ =>
        tuiMode = TUIMODE_INVALID_ACTION
    }
    tuiMode
  }

  def evaluateWin(inputStr: String): Int = {
    oldtuiMode = TUIMODE_WIN
    var inputW: Int = 0

    try {
      inputW = inputStr.toInt
    } catch {
      case _: NumberFormatException => return INVALID_INPUT
    }
    if (inputW == 1) {
      tuiMode = TUIMODE_MAINMENU
    } else if (inputW == 2) {
      tuiMode = TUIMODE_QUIT
    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
    }
    tuiMode
  }

  def evaluateLoose(inputStr: String): Int = {
    oldtuiMode = TUIMODE_LOSE
    var inputL: Int = 0

    try {
      inputL = inputStr.toInt
    } catch {
      case _: NumberFormatException => return INVALID_INPUT
    }
    if (inputL == 1) {

    } else if (inputL == 2) {

    }
    else {
      tuiMode = TUIMODE_INVALID_ACTION
    }
    tuiMode
  }

  var output: String = ""
  override def toString: String = {

    if (tuiMode >= 1) {
      output = output + banner
      if (tuiMode == TUIMODE_MAINMENU) {
        output = output + greetings + "\n"
        var index = 1
        for (x <- mainMenu) {
          output = output + "'" + index.toString + "': " + x + "\n"
          index += 1
        }
      }
      else if (tuiMode == TUIMODE_SELECTION) {
        output = "Level Selection" + "\n"
        var index = 1
        for (x <- controller.getImplementedLevels) {
          output = output + "'" + index.toString + "': " + controller.showLevel(x) + "\n"
          index += 1
        }
      }
    }
    else if (tuiMode == TUIMODE_RUNNING) {
      try {
        controller.playerStandsOnField()
        output = controller.levelToString + "\n" + "Player:" + "[ x: " + (controller.player.xPos + 1) + " | y: " + (controller.player.yPos + 1) + " ]" +
                                                            "\n" + "Ziel: [ x: " + (controller.level.winX + 1) + " | y: " + (controller.level.winY + 1) + "]" + "\n"
      } catch {
        case _: ArrayIndexOutOfBoundsException =>
          println("You fell off the trail!")
          println("Restart to begin a new game!")
          System.exit(0)
      }
    }
    else if (tuiMode == TUIMODE_INVALID_ACTION) {
      if (!output.contains("Ungültige Eingabe!")) {
        output += "Ungültige Eingabe!\n"
      }
    }
    else if (tuiMode == TUIMODE_WIN) {
      controller.playerStandsOnField()
      output = controller.levelToString + "\nCongratulations, you've found your way out of the dungeon!\n"
      var index = 2
        for (x <- winMenu) {
          output += "'" + index.toString + "': " + x + "\n"
          index += 1
        }
    }
    output
  }

  override def update(): Unit = printf("")
}


