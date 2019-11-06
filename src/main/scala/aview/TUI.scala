package main.scala.aview

import model._

class TUI {

  def processInputLine(input: String): Unit = {
    System.out.println("Welcome to TrailRunner")
    input match {
      case "s" =>
        (1 to 100) foreach { i =>
          val dots = "." * ((i - 1) / 10)
          print(s"\u001B[100D$i% $dots")
          Thread.sleep(10)
        }
      case "h" =>
        println("To start the game type 's'")
    }
  }

}
