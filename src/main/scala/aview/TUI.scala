package main.scala.aview

import java.io.FileNotFoundException

import model.{AllLevels, Player, maps}
import model.maps.Level
import model.maps.Level1

import scala.io.{BufferedSource, Source}
import scala.io.StdIn.readLine

class TUI {

  val greetings: String = "Welcome to TrailRunner!"
  val mainMenu: List[String] = List("Begin a new game!", "End game!")
  val banner = getTitleBanner()

  val TUIMODE_QUIT: Int = -1
  val TUIMODE_MAINMENU: Int = 1
  val TUIMODE_RUNNING: Int = 0
  val TUIMODE_SELECTION: Int = 2
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
    }
  }

  def evaluateRunning(input: String): Int = {

    input match {
      case "d" =>
        if (Level1.player.endGame == true) {
          Level1.player.moveRight
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "w" =>
        if (Level1.player.endGame == true) {
          Level1.player.moveUp
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "s" =>
        if (Level1.player.endGame == true) {
          Level1.player.moveDown
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
      case "a" =>
        if (Level1.player.endGame == true) {
          Level1.player.moveLeft
        }
        else {
          tuiMode = TUIMODE_MAINMENU
          return tuiMode
        }
    }
    tuiMode = TUIMODE_RUNNING
    tuiMode
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

  def evaluateSelection(inputStr: String): Int = {

    var input = 0

    try {
      input = inputStr.toInt
    }
    catch {
      case e: NumberFormatException => INVALID_INPUT
    }
    if(input == 1) {
      var chosenLevel = Level1.level
      tuiMode = TUIMODE_RUNNING
      tuiMode
    }
    else if(input == 2) {
      return 0
    }
    else {
      tuiMode = TUIMODE_MAINMENU
      tuiMode
    }
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
      output = Level1.level.map(_.mkString).mkString("\n") + "\n" + "You are here:" + "[ x: " + (Level1.player.xPos + 1) + " | y: " + (Level1.player.yPos + 1) + " ]" + "\n"
    }
    output
  }
}


