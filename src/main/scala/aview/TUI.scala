package main.scala.aview

import model.AllLevels
import scala.io.StdIn.readLine

class TUI {

  def processInputLine(input: String): Unit = {
    var ChosenLevel: String = ""
    var i: Int = 1
    input match {
      case "c" =>
        println("'n' - New game\n'q' - Quit the game\n")
      case "s" =>
        (1 to 100) foreach { i =>
          val dots = "." * ((i - 1) / 10)
          println(s"\u001B[100D$i% $dots")
          Thread.sleep(10)
        }
      case "n" =>
        println("Please select the level you like to play:")

        for (level <- AllLevels.implementedLevels) {
          print(s"'${i}' - ")
          i += 1
          AllLevels.showLevel(level)
        }
        ChosenLevel = readLine()
        ChosenLevel match {
          case "1" =>

          case "2" =>

        }

      //case "p" =>
        //println("Please enter your Name")
        //Level1.player1 = Player(readLine())
      case "h" =>
        println("To start the game type 's'")
    }
  }

}
