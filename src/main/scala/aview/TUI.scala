package main.scala.aview

import java.io.FileNotFoundException

import model.{AllLevels, Player, maps}
import model.maps.{Level, Level1, Level2}

import scala.io.{BufferedSource, Source}
import scala.io.StdIn.readLine

class TUI {

  val greetings: String = "Welcome to TrailRunner!"
  val mainMenu: List[String] = List("Begin a new game!", "End game!")
  val winMenu: List[String] = List("End game!")
  val banner = getTitleBanner()
  //val chosenLevel: List[]

  val TUIMODE_QUIT: Int = -1
  val TUIMODE_MAINMENU: Int = 1
  val TUIMODE_RUNNING: Int = 0
  val TUIMODE_SELECTION: Int = 2
  val TUIMODE_WIN: Int = -2
  val INVALID_INPUT: Int = 99
  var tuiMode = TUIMODE_MAINMENU

  private def getTitleBanner(): String = {
    var bufferedSource: BufferedSource = null
    try {
      bufferedSource = Source.fromFile("src/TrailRunnerStart.txt")
    } catch {
      case e: FileNotFoundException => bufferedSource = Source.fromFile("./src/TrailRunnerStart.txt")
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
    }
  }

  def evaluateMainMenu(inputStr: String): Int = {
    var input = 0

    try {
      input = inputStr.toInt
    } catch {
      case e: NumberFormatException => INVALID_INPUT
    }
    if (input == 1) {
      tuiMode = TUIMODE_SELECTION
      tuiMode
    } else if (input == 2) {
      tuiMode = TUIMODE_QUIT
      tuiMode
    }
    else
      INVALID_INPUT
  }

  var chosenLevel: Level = null

  def evaluateSelection(inputStr: String): Int = {

    var input = 0

    try {
      input = inputStr.toInt
    }
    catch {
      case e: NumberFormatException => INVALID_INPUT
    }
    if(input == 1) {
      chosenLevel = Level1
      tuiMode = TUIMODE_RUNNING
      return tuiMode
    }
    else if(input == 2) {
      chosenLevel = Level2
      tuiMode = TUIMODE_RUNNING
      tuiMode
    }
    else {
      tuiMode = TUIMODE_MAINMENU
      tuiMode
    }
  }

  def evaluateRunning(input: String): Int = {

    input match {
      case "d" =>
        if (chosenLevel.lose() == false) {
          chosenLevel.player.moveRight
          tuiMode = TUIMODE_RUNNING
          if (chosenLevel.player.xPos == chosenLevel.winX && chosenLevel.player.yPos == chosenLevel.winY && chosenLevel.sum() == 1) {
            tuiMode = TUIMODE_WIN
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "w" =>
        if (chosenLevel.lose() == false) {
          chosenLevel.player.moveUp
          tuiMode = TUIMODE_RUNNING
          if (chosenLevel.player.xPos == chosenLevel.winX && chosenLevel.player.yPos == chosenLevel.winY && chosenLevel.sum() == 1) {
            tuiMode = TUIMODE_WIN
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "s" =>
        if (chosenLevel.lose() == false) {
          chosenLevel.player.moveDown
          tuiMode = TUIMODE_RUNNING
          if (chosenLevel.player.xPos == chosenLevel.winX && chosenLevel.player.yPos == chosenLevel.winY && chosenLevel.sum() == 1) {
            tuiMode = TUIMODE_WIN
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "a" =>
        if (chosenLevel.lose() == false) {
          chosenLevel.player.moveLeft
          tuiMode = TUIMODE_RUNNING
          if (chosenLevel.player.xPos == chosenLevel.winX && chosenLevel.player.yPos == chosenLevel.winY && chosenLevel.sum() == 1) {
            tuiMode = TUIMODE_WIN
          }
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
    }
    tuiMode
  }

  def evaluateWin(inputStr: String): Int = {

    var input = 0

    try {
      input = inputStr.toInt
    } catch {
      case e: NumberFormatException => INVALID_INPUT
    }
    if (input == 1) {
      tuiMode = TUIMODE_MAINMENU
      tuiMode
    } else if (input == 2) {
      tuiMode = TUIMODE_QUIT
      tuiMode
    }
    else
      INVALID_INPUT
  }

  override def toString: String = {

    var output: String = ""

    if (tuiMode >= 1) {
      output = output + banner
      if (tuiMode == TUIMODE_MAINMENU) {
        output = output + greetings + "\n"
        var index = 1
        for (x <- mainMenu) {
          output = output + index.toString + ": " + x + "\n"
          index += 1
        }
      }
      else if (tuiMode == TUIMODE_SELECTION) {
        output = "Level Selection" + "\n"
        var index = 1
        for (x <- AllLevels.implementedLevels) {
          output = output + index.toString + ": " + AllLevels.showLevel(x) + "\n"
          index += 1
        }
      }
    }
    else if (tuiMode == TUIMODE_RUNNING) {
      try {
        chosenLevel.level(chosenLevel.player.yPos)(chosenLevel.player.xPos).PlayerStandsOnField
        output = chosenLevel.level.map(_.mkString).mkString("\n") + "\n" + "Player:" + "[ x: " + (chosenLevel.player.xPos + 1) + " | y: " + (chosenLevel.player.yPos + 1) + " ]" +
                                                            "\n" + "Ziel: [ x: " + (chosenLevel.winX + 1) + " | y: " + (chosenLevel.winY + 1) + "]" + "\n"
      } catch {
        case aoe: ArrayIndexOutOfBoundsException => {
          println("Restart to begin a new game!")
          System.exit(0)
        }
      }
    }
    else if (tuiMode == TUIMODE_WIN) {
      chosenLevel.level(chosenLevel.winY)(chosenLevel.winX).value -= 1
      output += chosenLevel.level.map(_.mkString).mkString("\n") + "\n"
      output += "You win!\n"
      var index = 2
      for (x <- winMenu) {
        output = output + index.toString + ": " + x + "\n"
        index += 1
      }
    }
    output
  }
}


